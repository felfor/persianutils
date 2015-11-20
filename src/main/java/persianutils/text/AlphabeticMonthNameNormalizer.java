package persianutils.text;

import java.util.HashMap;

/**
 * Assumes The Input Strings Are Normalized with WhitespaceNormalizer and
 * Character Normalizer Also sometimes makes extra spaces in the string
 */
public class AlphabeticMonthNameNormalizer implements TextNormalizer {
	public static final HashMap<String, Integer> monthName2MonthNumber;
	static {
		monthName2MonthNumber = new HashMap<String, Integer>();
		monthName2MonthNumber.put("فروردین", 1);
		monthName2MonthNumber.put("اردیبهشت", 2);
		monthName2MonthNumber.put("خرداد", 3);
		monthName2MonthNumber.put("تیر", 4);
		monthName2MonthNumber.put("مرداد", 5);
		monthName2MonthNumber.put("شهریور", 6);
		monthName2MonthNumber.put("مهر", 7);
		monthName2MonthNumber.put("ابان", 8);
		monthName2MonthNumber.put("اذر", 9);
		monthName2MonthNumber.put("دی", 10);
		monthName2MonthNumber.put("بهمن", 11);
		monthName2MonthNumber.put("اسفند", 12);

		monthName2MonthNumber.put("ژانویه", 1);
		monthName2MonthNumber.put("فوریه", 2);
		monthName2MonthNumber.put("مارس", 3);
		monthName2MonthNumber.put("آوریل", 4);
		monthName2MonthNumber.put("مه", 5);
		monthName2MonthNumber.put("ژوئن", 6);
		monthName2MonthNumber.put("ژوئیه", 7);
		monthName2MonthNumber.put("اوت", 8);
		monthName2MonthNumber.put("سپتامبر", 9);
		monthName2MonthNumber.put("اکتبر", 10);
		monthName2MonthNumber.put("نوامبر", 11);
		monthName2MonthNumber.put("دسامبر", 12);

		monthName2MonthNumber.put("january", 1);
		monthName2MonthNumber.put("february", 2);
		monthName2MonthNumber.put("march", 3);
		monthName2MonthNumber.put("april", 4);
		monthName2MonthNumber.put("may", 5);
		monthName2MonthNumber.put("june", 6);
		monthName2MonthNumber.put("july", 7);
		monthName2MonthNumber.put("august", 8);
		monthName2MonthNumber.put("september", 9);
		monthName2MonthNumber.put("october", 10);
		monthName2MonthNumber.put("november", 11);
		monthName2MonthNumber.put("december", 12);
		
		monthName2MonthNumber.put("jan", 1);
		monthName2MonthNumber.put("feb", 2);
		monthName2MonthNumber.put("mar", 3);
		monthName2MonthNumber.put("apr", 4);
		monthName2MonthNumber.put("jun", 6);
		monthName2MonthNumber.put("jul", 7);
		monthName2MonthNumber.put("aug", 8);
		monthName2MonthNumber.put("sep", 9);
		monthName2MonthNumber.put("oct", 10);
		monthName2MonthNumber.put("nov", 11);
		monthName2MonthNumber.put("dec", 12);
		
	}

	@Override
	public String normalize(String textToNormalize) {
		textToNormalize = textToNormalize.replaceAll("ماه", "");
		StringBuilder normalizedText = new StringBuilder();
		TextTokenizer tokenizer = new OnPersianAndEnglishContextSwitchTokenizer();
		for (String token : tokenizer.tokenize(textToNormalize))
			normalizedText.append(monthName2MonthNumber(token));
		return normalizedText.toString();
	}

	private String monthName2MonthNumber(String word) {
		return isMonthName(word) ? wrapWithSpace(monthName2MonthNumber.get(word)) : word;
	}

	private boolean isMonthName(String str) {
		return monthName2MonthNumber.containsKey(str);
	}

	private <T> String wrapWithSpace(T item) {
		return String.format(" %s ", item);
	}
}
