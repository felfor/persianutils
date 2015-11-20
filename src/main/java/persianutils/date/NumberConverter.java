package persianutils.date;


public class NumberConverter {
	public String convertToEnglishNumber(String str) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) > 1775 && str.charAt(i) < 1786) {
				builder.append((char) (str.charAt(i) - 1728));
			} else if (str.charAt(i) > 1631 && str.charAt(i) < 1642) {

				builder.append((char) (str.charAt(i) - 1584));

			} else
				builder.append(str.charAt(i));

		}
		return builder.toString();
	}
public static void main(String[] args) {
	System.out.println(new NumberConverter().convertToEnglishNumber("شتاریخ انتشار : پنجشنبه ۱۰ مهر ۱۳۹۳ ساعت ۱۴:۵۳ 17:00 "));
}
}
