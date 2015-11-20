package persianutils.text;

import static persianutils.text.StringHelpers.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
// Note : Assumes That Input String Has Been Normalized With WhitespaceNormalizer
// and CharacterNormalizer
public class AlphabeticNumberNormalizer implements TextNormalizer {
  private static HashMap<String, Integer> persianAlphabeticalNumbers1To99;
  static {
    persianAlphabeticalNumbers1To99 = new HashMap<String, Integer>();
    persianAlphabeticalNumbers1To99.put("یک", 1);
    persianAlphabeticalNumbers1To99.put("دو", 2);
    persianAlphabeticalNumbers1To99.put("سه", 3);
    persianAlphabeticalNumbers1To99.put("چهار", 4);
    persianAlphabeticalNumbers1To99.put("پنج", 5);
    persianAlphabeticalNumbers1To99.put("شش", 6);
    persianAlphabeticalNumbers1To99.put("هفت", 7);
    persianAlphabeticalNumbers1To99.put("هشت", 8);
    persianAlphabeticalNumbers1To99.put("نه", 9);
    persianAlphabeticalNumbers1To99.put("ده", 10);
    persianAlphabeticalNumbers1To99.put("یازده", 11);
    persianAlphabeticalNumbers1To99.put("دوازده", 12);
    persianAlphabeticalNumbers1To99.put("سیزده", 13);
    persianAlphabeticalNumbers1To99.put("چهارده", 14);
    persianAlphabeticalNumbers1To99.put("پانزده", 15);
    persianAlphabeticalNumbers1To99.put("شانزده", 16);
    persianAlphabeticalNumbers1To99.put("هفده", 17);
    persianAlphabeticalNumbers1To99.put("هجده", 18);
    persianAlphabeticalNumbers1To99.put("نوزده", 19);
    persianAlphabeticalNumbers1To99.put("بیست", 20);
    persianAlphabeticalNumbers1To99.put("بیست و یک", 21);
    persianAlphabeticalNumbers1To99.put("بیست و دو", 22);
    persianAlphabeticalNumbers1To99.put("بیست و سه", 23);
    persianAlphabeticalNumbers1To99.put("بیست و چهار", 24);
    persianAlphabeticalNumbers1To99.put("بیست و پنج", 25);
    persianAlphabeticalNumbers1To99.put("بیست و شش", 26);
    persianAlphabeticalNumbers1To99.put("بیست و هفت", 27);
    persianAlphabeticalNumbers1To99.put("بیست و هشت", 28);
    persianAlphabeticalNumbers1To99.put("بیست و نه", 29);
    persianAlphabeticalNumbers1To99.put("سی", 30);
    persianAlphabeticalNumbers1To99.put("سی و یک", 31);
    persianAlphabeticalNumbers1To99.put("سی و دو", 32);
    persianAlphabeticalNumbers1To99.put("سی و سه", 33);
    persianAlphabeticalNumbers1To99.put("سی و چهار", 34);
    persianAlphabeticalNumbers1To99.put("سی و پنج", 35);
    persianAlphabeticalNumbers1To99.put("سی و شش", 36);
    persianAlphabeticalNumbers1To99.put("سی و هفت", 37);
    persianAlphabeticalNumbers1To99.put("سی و هشت", 38);
    persianAlphabeticalNumbers1To99.put("سی و نه", 39);
    persianAlphabeticalNumbers1To99.put("چهل", 40);
    persianAlphabeticalNumbers1To99.put("چهل و یک", 41);
    persianAlphabeticalNumbers1To99.put("چهل و دو", 42);
    persianAlphabeticalNumbers1To99.put("چهل و سه", 43);
    persianAlphabeticalNumbers1To99.put("چهل و چهار", 43);
    persianAlphabeticalNumbers1To99.put("چهل و پنج", 45);
    persianAlphabeticalNumbers1To99.put("چهل و شش", 46);
    persianAlphabeticalNumbers1To99.put("چهل و هفت", 47);
    persianAlphabeticalNumbers1To99.put("چهل و هشت", 48);
    persianAlphabeticalNumbers1To99.put("چهل و نه", 49);
    persianAlphabeticalNumbers1To99.put("پنجاه", 50);
    persianAlphabeticalNumbers1To99.put("پنجاه و یک", 51);
    persianAlphabeticalNumbers1To99.put("پنجاه و دو", 52);
    persianAlphabeticalNumbers1To99.put("پنجاه و سه", 53);
    persianAlphabeticalNumbers1To99.put("پنجاه و چهار", 54);
    persianAlphabeticalNumbers1To99.put("پنجاه و پنج", 55);
    persianAlphabeticalNumbers1To99.put("پنجاه و شش", 56);
    persianAlphabeticalNumbers1To99.put("پنجاه و هفت", 57);
    persianAlphabeticalNumbers1To99.put("پنجاه و هشت", 58);
    persianAlphabeticalNumbers1To99.put("پنجاه و نه", 59);
    persianAlphabeticalNumbers1To99.put("شصت", 60);
    persianAlphabeticalNumbers1To99.put("شصت و یک", 61);
    persianAlphabeticalNumbers1To99.put("شصت و دو", 62);
    persianAlphabeticalNumbers1To99.put("شصت و سه", 63);
    persianAlphabeticalNumbers1To99.put("شصت و چهار", 64);
    persianAlphabeticalNumbers1To99.put("شصت و پنج", 65);
    persianAlphabeticalNumbers1To99.put("شصت و شش", 66);
    persianAlphabeticalNumbers1To99.put("شصت و هفت", 67);
    persianAlphabeticalNumbers1To99.put("شصت و هشت", 68);
    persianAlphabeticalNumbers1To99.put("شصت و نه", 69);
    persianAlphabeticalNumbers1To99.put("هفتاد", 70);
    persianAlphabeticalNumbers1To99.put("هفتاد و یک", 71);
    persianAlphabeticalNumbers1To99.put("هفتاد و دو", 72);
    persianAlphabeticalNumbers1To99.put("هفتاد و سه", 73);
    persianAlphabeticalNumbers1To99.put("هفتاد و چهار", 74);
    persianAlphabeticalNumbers1To99.put("هفتاد و پنج", 75);
    persianAlphabeticalNumbers1To99.put("هفتاد و شش", 76);
    persianAlphabeticalNumbers1To99.put("هفتاد و هفت", 77);
    persianAlphabeticalNumbers1To99.put("هفتاد و هشت", 78);
    persianAlphabeticalNumbers1To99.put("هفتاد و نه", 79);
    persianAlphabeticalNumbers1To99.put("هشتاد", 80);
    persianAlphabeticalNumbers1To99.put("هشتاد و یک", 81);
    persianAlphabeticalNumbers1To99.put("هشتاد و دو", 82);
    persianAlphabeticalNumbers1To99.put("هشتاد و سه", 83);
    persianAlphabeticalNumbers1To99.put("هشتاد و چهار", 84);
    persianAlphabeticalNumbers1To99.put("هشتاد و پنج", 85);
    persianAlphabeticalNumbers1To99.put("هشتاد و شش", 86);
    persianAlphabeticalNumbers1To99.put("هشتاد و هفت", 87);
    persianAlphabeticalNumbers1To99.put("هشتاد و هشت", 88);
    persianAlphabeticalNumbers1To99.put("هشتاد و نه", 89);
    persianAlphabeticalNumbers1To99.put("نود", 90);
    persianAlphabeticalNumbers1To99.put("نود و یک", 91);
    persianAlphabeticalNumbers1To99.put("نود و دو", 92);
    persianAlphabeticalNumbers1To99.put("نود و سه", 93);
    persianAlphabeticalNumbers1To99.put("نود و چهار", 94);
    persianAlphabeticalNumbers1To99.put("نود و پنج", 95);
    persianAlphabeticalNumbers1To99.put("نود و شش", 96);
    persianAlphabeticalNumbers1To99.put("نود و هفت", 97);
    persianAlphabeticalNumbers1To99.put("نود و هشت", 98);
    persianAlphabeticalNumbers1To99.put("نود و نه", 99);
  }

//  TODO: Extract Methods
  @Override
  public String normalize(String textToNormalize) {
    StringBuilder normalizedText = new StringBuilder();
    LinkedList<String> tokens = toTokens(textToNormalize);
    while (!tokens.isEmpty()) {
      Integer num = null;

      if (canParseNWords(tokens, 5)) {
        num = tryParseNWords(tokens, 5);
        normalizedText.append(num);
        popNBeginningLinkedList(tokens, 5);
        continue;
      }

      if (canParseNWords(tokens, 1)) {
        num = tryParseNWords(tokens, 1);
        normalizedText.append(num.toString());
        popNBeginningLinkedList(tokens, 1);
        continue;
      }

      normalizedText.append(tokens.peekFirst());
      tokens.removeFirst();
    }
    return normalizedText.toString();
  }

  private boolean canParseNWords(LinkedList<String> tokens, int n) {
    return tryParseNWords(tokens, n) != null;
  }

  private Integer tryParseNWords(LinkedList<String> tokens , int n) {
    final String numberCandidate =
        join(Arrays.asList(takeFromLinkedList(tokens, n)), "")
        .replace("سوم", "سهم");
    return stringToInteger(
        numberCandidate.endsWith("م") ?
            numberCandidate.substring(0, numberCandidate.length() - 1):
            numberCandidate);
  }

  private String[] takeFromLinkedList(LinkedList<String> tokens, int n) {
    int m = Math.min(n, tokens.size());
    String[] elems = new String[n];
    for (  int i = 0;  i < m; i++) elems[i] = tokens.pollFirst();
    for (int i = m-1; i >= 0; i--) tokens.addFirst(elems[i]);
    return elems;
  }

  private void popNBeginningLinkedList(LinkedList<String> tokens, int n) {
    int m = Math.min(n, tokens.size());
    for (int i = 0; i < m; i++) tokens.removeFirst();
  }

  // Null if it's not an integer
  private Integer stringToInteger(String number) {
    return persianAlphabeticalNumbers1To99.containsKey(number) ?
        persianAlphabeticalNumbers1To99.get(number) : null;
  }

  private LinkedList<String> toTokens(String str) {
    LinkedList<String> tokens = new LinkedList<String>();
    for (String token : new OnPersianContextSwitchTokenizer().tokenize(str))
      tokens.addLast(token);
    return tokens;
  }
}