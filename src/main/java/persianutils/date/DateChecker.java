package persianutils.date;

import org.apache.log4j.Logger;

public class DateChecker {
	private static final Logger LOG = Logger.getLogger(DateChecker.class);

	public static String check(String date, String format)  {
		String result = null;
		try {
			LOG.info(String.format("Method:check(%s,%s)", date, format));

			DateUtil util = new DateUtil();
			result = util.extract(date, format);
		} catch (Exception e) {
			LOG.error(e);
		}
		return result;
	}
}
