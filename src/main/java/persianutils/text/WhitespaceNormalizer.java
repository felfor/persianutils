package persianutils.text;

public class WhitespaceNormalizer implements TextNormalizer {
  public static final char SEMI_SPACE = '\u200C';

  @Override
  public String normalize(String textToNormalize) {
    return textToNormalize.replaceAll("[ \t\n\r" + SEMI_SPACE + "]+", " ");
  }
}
