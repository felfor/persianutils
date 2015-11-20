package persianutils.text;

import java.util.Iterator;

public abstract class OnContextSwitchTokenizer implements TextTokenizer {
  @Override
  public Iterable<String> tokenize(final String textToTokenize) {
    return new Iterable<String>() {
      @Override
      public Iterator<String> iterator() {
        return new TokensIterator(textToTokenize);
      }
    };
  }

  abstract boolean isContextSwitch(char l, char r);

  public class TokensIterator implements Iterator<String> {
    private String remainingPart;

    public TokensIterator(String textToTokenize) {
      remainingPart = textToTokenize;
    }

    @Override
    public boolean hasNext() {
      return !remainingPart.isEmpty();
    }

    @Override
    public String next() {
      if (!hasNext()) throw new OnContextSwitchTokenizer.NoSuchElementException();
      int boundary;
      for (boundary = 1; boundary < remainingPart.length(); boundary++)
        if (isContextSwitch(remainingPart.charAt(boundary-1)
            , remainingPart.charAt(boundary))) break;
      String part = remainingPart.substring(0, boundary);
      remainingPart = remainingPart.substring(boundary);
      return part;
    }

    @Override
    public void remove() {
      throw new OnContextSwitchTokenizer.RemoveNotSupported();
    }
  }

  public class RemoveNotSupported extends RuntimeException {
  }

  public class NoSuchElementException extends RuntimeException {
  }
}
