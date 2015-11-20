package persianutils.text;

import java.util.HashMap;
import java.util.List;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

public class PMDetector {
	private static HashMap<String, String> normalPM;

	static {
		normalPM = new HashMap<String, String>();
		normalPM.put("PM", "T");
		normalPM.put("pm", "T");
		normalPM.put("بعدازظهر", "T");
		normalPM.put("P", "PM");
		normalPM.put("p", "pm");
		normalPM.put("ب", "بظ");
		normalPM.put("بعد", "بعدازظهر");
	}

	public boolean hasPM(String str) {
		@SuppressWarnings("static-access")
		List<String> list = Splitter.on(CharMatcher.BREAKING_WHITESPACE).on(CharMatcher.WHITESPACE)
				.onPattern("[\\.:  ]").omitEmptyStrings().trimResults().splitToList(str);
		String[] split = list.toArray(new String[list.size()]);
		for (int i = 0; i < split.length; i++) {
			String val = normalPM.get(split[i]);
			if (val != null) {
				if ("T".equals(val))
					return true;
				if ((i < split.length - 1 && val.equals(split[i] + split[i + 1]))
						|| (i < split.length - 2 && val.equals(split[i] + split[i + 1] + split[i + 2])))
					return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.err.println(new PMDetector().hasPM("12 2 3 : pm"));
		System.err.println(new PMDetector().hasPM("12 2 3 : PM"));
		System.err.println(new PMDetector().hasPM("12 2 3 : بعد از ظهر"));
		System.err.println(new PMDetector().hasPM("12 2 3 : p.m"));
		System.err.println(new PMDetector().hasPM("12 2 3 : P.M"));
		System.err.println(new PMDetector().hasPM("12 2 3 : ب.ظ"));

	}

}
