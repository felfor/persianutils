package persianutils.text;

public interface TextTokenizer {
  Iterable<String> tokenize(String textToTokenize);
}
