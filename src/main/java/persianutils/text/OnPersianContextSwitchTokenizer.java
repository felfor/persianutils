package persianutils.text;

import java.util.HashSet;

public class OnPersianContextSwitchTokenizer extends OnContextSwitchTokenizer {
  private static HashSet<Character> persianAlphabet;
  static {
    persianAlphabet = new HashSet<Character>();
    persianAlphabet.add('آ');
    persianAlphabet.add('ا');
    persianAlphabet.add('ب');
    persianAlphabet.add('پ');
    persianAlphabet.add('ت');
    persianAlphabet.add('ث');
    persianAlphabet.add('ج');
    persianAlphabet.add('چ');
    persianAlphabet.add('ح');
    persianAlphabet.add('خ');
    persianAlphabet.add('د');
    persianAlphabet.add('ذ');
    persianAlphabet.add('ر');
    persianAlphabet.add('ز');
    persianAlphabet.add('ژ');
    persianAlphabet.add('س');
    persianAlphabet.add('ش');
    persianAlphabet.add('ص');
    persianAlphabet.add('ض');
    persianAlphabet.add('ط');
    persianAlphabet.add('ظ');
    persianAlphabet.add('ع');
    persianAlphabet.add('غ');
    persianAlphabet.add('ف');
    persianAlphabet.add('ق');
    persianAlphabet.add('ک');
    persianAlphabet.add('گ');
    persianAlphabet.add('ل');
    persianAlphabet.add('م');
    persianAlphabet.add('ن');
    persianAlphabet.add('و');
    persianAlphabet.add('ه');
    persianAlphabet.add('ی');
    persianAlphabet.add('ئ');
    persianAlphabet.add('ى');
  }
  

  @Override
  boolean isContextSwitch(char l, char r) {
	  return isPersian(l)^isPersian(r);
  }

  boolean isPersian(char c) {
    return persianAlphabet.contains(c);
  }
}