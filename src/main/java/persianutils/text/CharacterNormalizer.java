package persianutils.text;

public class CharacterNormalizer implements TextNormalizer {
	public static final char SEMI_SPACE = '\u200C';
	public static final char ARABIC_YE = '\u064A';
	public static final char PERSIAN_YE = '\u06CC';
	public static final char OTHER_YE = '\u0649';
	public static final char PERSIAN_KE = '\u06A9';
	public static final char ARABIC_KE = '\u0643';
	public static final char ARABIC_ZERO = '\u06f0';
	public static final char ARABIC_NINE = '\u06f9';
	public static final char PERSIAN_ZERO = '\u0660';
	public static final char PERSIAN_NINE = '\u0669';

	@Override
	public String normalize(String textToNormalize) {
		StringBuilder normalizedText = new StringBuilder();
		for (char c : textToNormalize.toCharArray())
			normalizedText.append(normalize(c));
		return normalizedText.toString().replaceAll(Character.toString(SEMI_SPACE), "");
	}

	private char normalize(char c) {
		return Character
				.toLowerCase(arabicDigitToEnglishDigit(persianDigitToEnglishDigit(transformCharToDefaultHomomorphism(c))));
	}

	private char persianDigitToEnglishDigit(char c) {
		return isPersianDigit(c) ? (char) (c - PERSIAN_ZERO + '0') : c;
	}

	private boolean isPersianDigit(char c) {
		return Character.compare(c, PERSIAN_ZERO) >= 0 && Character.compare(c, PERSIAN_NINE) <= 0;
	}

	private char arabicDigitToEnglishDigit(char c) {
		return isArabicDigit(c) ? (char) (c - ARABIC_ZERO + '0') : c;
	}

	private boolean isArabicDigit(char c) {
		return Character.compare(c, ARABIC_ZERO) >= 0 && Character.compare(c, ARABIC_NINE) <= 0;
	}

	private char transformCharToDefaultHomomorphism(char c) {
		switch (c) {
		case ARABIC_KE:
			return PERSIAN_KE;
		case ARABIC_YE:
			return PERSIAN_YE;
		case 'آ':
			return 'ا';
		case OTHER_YE:
			return PERSIAN_YE;
		default:
			return c;
		}
	}
}
