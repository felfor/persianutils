package persianutils.text;

public class TextNormalizers {
  public static TextNormalizer compose(final TextNormalizer... normalizers) {
    return new TextNormalizer() {
      @Override
      public String normalize(String textToNormalize) {
        String currStr = textToNormalize;
        for (int i = normalizers.length - 1; i >= 0; i--)
          currStr = normalizers[i].normalize(currStr);
        return currStr;
      }
    };
  }
}
