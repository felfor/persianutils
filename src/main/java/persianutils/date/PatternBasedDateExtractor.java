package persianutils.date;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import persianutils.text.AlphabeticMonthNameNormalizer;
import persianutils.text.AlphabeticNumberNormalizer;
import persianutils.text.CharacterNormalizer;
import persianutils.text.StringHelpers;
import persianutils.text.TextNormalizer;
import persianutils.text.TextNormalizers;

public class PatternBasedDateExtractor implements DateExtractor<String> {
	public static final HashMap<String, DateTimeField> pattern2DateTimeField;
	static {
		pattern2DateTimeField = new HashMap<String, DateTimeField>();
		pattern2DateTimeField.put("per_y", DateTimeField.JalaliYear);
		pattern2DateTimeField.put("per_m", DateTimeField.JalaliMonth);
		pattern2DateTimeField.put("per_d", DateTimeField.JalaliDay);
		pattern2DateTimeField.put("ger_y", DateTimeField.GregorianYear);
		pattern2DateTimeField.put("ger_m", DateTimeField.GregorianMonth);
		pattern2DateTimeField.put("ger_d", DateTimeField.GregorianDay);
		pattern2DateTimeField.put("hh", DateTimeField.Hour);
		pattern2DateTimeField.put("mm", DateTimeField.Minute);
		pattern2DateTimeField.put("ss", DateTimeField.Second);
	}

	public static final HashMap<DateTimeField, String> field2Regex;
	static {
		field2Regex = new HashMap<DateTimeField, String>();
		field2Regex.put(DateTimeField.JalaliYear, "(?:13)?\\d\\d");
		field2Regex.put(DateTimeField.JalaliMonth, "1[0-2]|0?\\d");
		field2Regex.put(DateTimeField.JalaliDay, "3[01]|[12]\\d|0?\\d");
		field2Regex.put(DateTimeField.GregorianYear, "19\\d{2}|2[01]\\d{2}");
		field2Regex.put(DateTimeField.GregorianMonth, "1[0-2]|0?\\d");
		field2Regex.put(DateTimeField.GregorianDay, "3[01]|[12]\\d|0?\\d");
		field2Regex.put(DateTimeField.Hour, "2[0-4]|([01]?\\d)");
		field2Regex.put(DateTimeField.Minute, "[0-5]?\\d");
		field2Regex.put(DateTimeField.Second, "[0-5]?\\d");
	}

	/**
   */
	@Override
	public Map<DateTimeField, Integer> extract(String input, String pattern) {
		List<DateTimeField> fieldsToExtract = patternToFields(pattern.toLowerCase());
		Pattern extractionPattern = fieldNamesToExtractionPattern(fieldsToExtract);
		Matcher matcher = extractionPattern.matcher(input);
		if (matcher.find())
			return extractDateTimeFromMatcher(fieldsToExtract, matcher);
		throw new MatchingFailureException();
	}

	private List<DateTimeField> patternToFields(String pattern) {
		List<String> fieldsNames = new LinkedList<String>(Arrays.asList(pattern.toLowerCase().split("@")));

		List<DateTimeField> fields = new ArrayList<DateTimeField>();

		Iterator<String> it = fieldsNames.iterator();
		while (it.hasNext()) {
			final String currField = it.next();
			if (isEmptyOrIgnore(currField))
				it.remove();
			else if (!isValidFieldName(currField))
				throw new BadPatternException();
			else
				fields.add(fieldName2DateTimeField(currField));
		}

		return fields;
	}

	private DateTimeField fieldName2DateTimeField(String currField) {
		return pattern2DateTimeField.get(currField);
	}

	private boolean isValidFieldName(String str) {
		return pattern2DateTimeField.containsKey(str);
	}

	private boolean isEmptyOrIgnore(String str) {
		return str.equals("ign") || str.isEmpty();
	}

	private Pattern fieldNamesToExtractionPattern(List<DateTimeField> fieldNames) {
		List<String> regexes = new ArrayList<String>();
		for (DateTimeField dateTimeField : fieldNames)
			regexes.add(dateTimeField2RegexGroup(dateTimeField));
		return Pattern.compile(String.format(".*?%s.*?", StringHelpers.join(regexes, "\\D+?")));
	}

	private String dateTimeField2RegexGroup(DateTimeField dateTimeField) {
		return String.format("(?<%s>%s)", dateTimeField.name(), field2Regex.get(dateTimeField));
	}

	private HashMap<DateTimeField, Integer> extractDateTimeFromMatcher(List<DateTimeField> fieldsToExtract,
			Matcher matcher) {
		HashMap<DateTimeField, Integer> dateFields = new HashMap<DateTimeField, Integer>();
		for (DateTimeField field : fieldsToExtract)
			if (matcher.group(field.name()) != null)
				dateFields.put(field, Integer.parseInt(matcher.group(field.name())));
		return dateFields;
	}

	public static TextNormalizer makeNormalizer() {
		return TextNormalizers.compose(new TextNormalizer() {
			@Override
			public String normalize(String textToNormalize) {
				return textToNormalize.replaceAll("\\D\\d\\s*شنبه", "").replaceAll("^\\d?\\s*شنبه", "").replaceAll("\\D+", " ");
			}
		}, new AlphabeticNumberNormalizer(), new AlphabeticMonthNameNormalizer(), new CharacterNormalizer());
	}

	public static DateExtractor<String> makeDateExtractorWithNormalizer() {
		return new DateExtractor<String>() {
			TextNormalizer normalizer = makeNormalizer();
			DateExtractor<String> dateExtractor = new PatternBasedDateExtractor();

			@Override
			public Map<DateTimeField, Integer> extract(String input, String pattern) {
				return dateExtractor.extract(normalizer.normalize(input), correctPattern(pattern));
			}

			private String correctPattern(String pattern) {
				pattern.replaceAll(" ", "");
				StringBuilder builders = new StringBuilder();
				for (String token : pattern.split("@"))
					if (!shouldTokenBeRemoved(token))
						builders.append("@" + correctPatternToken(token));
				return builders.length() == 0 ? "" : builders.substring(1);
			}

			private boolean shouldTokenBeRemoved(String token) {
				final String tokenLower = token.toLowerCase();
				return token.isEmpty() || tokenLower.equals("ign") || tokenLower.equals("wek_d")
						|| tokenLower.equals("per_w");
			}

			private String correctPatternToken(String token) {
				switch (token) {
				case "PWER_Y":
					return "PER_Y";
				case "PWER_M":
					return "PER_M";
				case "PWER_D":
					return "PER_D";
				default:
					return token;
				}
			}
		};
	}

	public class MatchingFailureException extends RuntimeException {
	}

	public class BadPatternException extends RuntimeException {
	}
}
