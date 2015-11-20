package persianutils.date;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import persianutils.date.JalaliCalendar.YearMonthDate;
import persianutils.text.PMDetector;


public class DateUtil {
	private static final Logger log = Logger.getLogger(DateUtil.class);
	private DateExtractor<String> dateExtractor;
	private PMDetector pmDetector;

	public DateUtil() {
		dateExtractor = PatternBasedDateExtractor.makeDateExtractorWithNormalizer();
		pmDetector=new PMDetector();
	}

	public String extract(String input, String extractFormat) {
		try {
			Map<DateTimeField, Integer> dateExtracted = dateExtractor.extract(input, extractFormat);
			if(pmDetector.hasPM(input)&&dateExtracted.get(DateTimeField.Hour)!=null){
				dateExtracted.put(DateTimeField.Hour, dateExtracted.get(DateTimeField.Hour)+12);
			}
					
			dateExtracted = withGregorianDate(dateExtracted);

			return String.format("%04d-%02d-%02dT%02d:%02d:%02dZ",
					nullToZero(dateExtracted.get(DateTimeField.GregorianYear)),
					nullToZero(dateExtracted.get(DateTimeField.GregorianMonth)),
					nullToZero(dateExtracted.get(DateTimeField.GregorianDay)),
					nullToZero(dateExtracted.get(DateTimeField.Hour)),
					nullToZero(dateExtracted.get(DateTimeField.Minute)),
					nullToZero(dateExtracted.get(DateTimeField.Second)));
		} catch (Exception exception) {
			log.error("unable to parse date:" + input + " with pattern:" + extractFormat);
			return "0000-00-00T00:00:00Z";
		}

	}

	// Returns the hash passed with Gregorian Date Included if can be deduced
	// from other properties
	private Map<DateTimeField, Integer> withGregorianDate(Map<DateTimeField, Integer> dateExtracted) {
		if (isGregorianDate(dateExtracted) || !isJalaliDate(dateExtracted))
			return dateExtracted;
		Map<DateTimeField, Integer> dateWithGregorian = new HashMap<DateTimeField, Integer>(dateExtracted);

		int year = dateExtracted.get(DateTimeField.JalaliYear) < 100 ? 1300 + dateExtracted
				.get(DateTimeField.JalaliYear) : dateExtracted.get(DateTimeField.JalaliYear);
		int month = dateExtracted.get(DateTimeField.JalaliMonth);
		int day = dateExtracted.get(DateTimeField.JalaliDay);

		YearMonthDate gregorianDate = JalaliCalendar.jalaliToGregorian(new JalaliCalendar.YearMonthDate(year,
				month - 1, day));

		dateWithGregorian.put(DateTimeField.GregorianYear, gregorianDate.getYear());
		dateWithGregorian.put(DateTimeField.GregorianMonth, gregorianDate.getMonth()+1);
		dateWithGregorian.put(DateTimeField.GregorianDay, gregorianDate.getDate());

		return dateWithGregorian;
	}

	private boolean isGregorianDate(Map<DateTimeField, Integer> dateExtracted) {
		return dateExtracted.containsKey(DateTimeField.GregorianYear)
				&& dateExtracted.containsKey(DateTimeField.GregorianMonth)
				&& dateExtracted.containsKey(DateTimeField.GregorianDay);
	}

	private boolean isJalaliDate(Map<DateTimeField, Integer> dateExtracted) {
		return dateExtracted.containsKey(DateTimeField.JalaliYear)
				&& dateExtracted.containsKey(DateTimeField.JalaliMonth)
				&& dateExtracted.containsKey(DateTimeField.JalaliDay);
	}

	private int nullToZero(Integer integer) {
		return integer == null ? 0 : integer;
	}

	public static void main(String[] args) {
//	  	String str="تاریخ انتشار : چهارشنبه 10 دى 1393   pm   8:28 ";
String str="جمعه ۲۹ خرداد ۱۳۹۴ ساعت ۱۳:۵۷";
		StringTokenizer streamTokenizer = new StringTokenizer(str, " :");
		while (streamTokenizer.hasMoreElements()) {
			System.out.println(streamTokenizer.nextElement());

		}
		String pattern = "IGN@IGN@IGN@PER_D@PER_M@PER_Y@HH@MM";
		DateUtil dateUtil = new DateUtil();
		System.out.println(dateUtil.extract(str, pattern));

	}
}