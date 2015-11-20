package persianutils.date;

import java.text.ParseException;
import java.util.Date;

import com.ghasemkiani.util.icu.PersianCalendar;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.ULocale;

public class GerToPer {

	String convert(String in, String inFormat, String outFormat) throws ParseException {
		ULocale locale = new ULocale("fa_IR@calendar=persian");
		DateFormat iDF = new SimpleDateFormat(inFormat);
		DateFormat oDF = DateFormat.getPatternInstance(outFormat, locale);
		Date parse = iDF.parse(in);
		PersianCalendar persianCalendar = new PersianCalendar(parse);
		return oDF.format(persianCalendar);
	}

	public String convert(long in, String outFormat) {
		ULocale locale = new ULocale("fa_IR@calendar=persian");
		DateFormat oDF = DateFormat.getPatternInstance(outFormat, locale);
		PersianCalendar persianCalendar = new PersianCalendar();
		persianCalendar.setTimeInMillis(in);
		return oDF.format(persianCalendar);
	}

	public static void main(String[] args) throws ParseException {

//		System.out.println(new GerToPer().convert("2015-03-21T13:13:13Z", "yyyy-MM-dd'T'HH:mm:ss'Z'",
//				"yyyy-MM-dd'T'HH:mm:ss'Z'"));
//		System.out.println(new GerToPer().convert(1433766107749L, "yyyy-MM-dd'T'HH:mm:ss'Z'"));
		System.out.println(new GerToPer().convert(1433766107749L, "HH:mm:ss' ساعت 'yyyy-MM-dd"));


	}
}
