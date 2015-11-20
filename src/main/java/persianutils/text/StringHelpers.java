package persianutils.text;

import java.util.Iterator;

import static java.lang.Math.min;

public class StringHelpers {
    public static String join(Iterable<String> words, String delimiter) {
        StringBuilder joined = new StringBuilder();
        Iterator<String> wordsIterator = words.iterator();
        if (wordsIterator.hasNext()) joined.append(wordsIterator.next());
        while (wordsIterator.hasNext())
            joined.append(delimiter + wordsIterator.next());
        return joined.toString();
    }

    public static boolean endsWithPrefixes(String str, String[] prefixes) {
        for (String prefix : prefixes) if (str.endsWith(prefix)) return true;
        return false;
    }

    public static String commonPrefix(String fst, String snd) {
        StringBuilder cp = new StringBuilder();
        int minLength = Math.min(fst.length(), snd.length());
        fst = fst.substring(0, minLength);
        snd = snd.substring(0, minLength);

        for (int i = 0; i < minLength && fst.charAt(i) == snd.charAt(i); i++)
            cp.append(fst.charAt(i));

        return cp.toString();
    }

    public static String commonPostfix(String fst, String snd) {
        StringBuilder cp = new StringBuilder();
        fst = fst.substring(Math.max(0, fst.length() - snd.length()), fst.length());
        snd = snd.substring(Math.max(0, snd.length() - fst.length()), snd.length());

        for (int i = fst.length() - 1; i >= 0 && fst.charAt(i) == snd.charAt(i); i--)
            cp.insert(0, fst.charAt(i));

        return cp.toString();
    }

    public static String replaceDifference(String fst, String snd, String replaceWith) {
        if (fst.equals(snd)) return replaceWith;

        String prefix = StringHelpers.commonPrefix(fst, snd);
        String postfix = StringHelpers.commonPostfix(fst, snd);

        int minStrLength = Math.min(fst.length(), snd.length());
        int fixesLengthSum = prefix.length() + postfix.length();

        return fixesLengthSum <= minStrLength ? prefix + replaceWith + postfix :
                prefix + replaceWith + postfix.substring(fixesLengthSum - minStrLength);
    }
}