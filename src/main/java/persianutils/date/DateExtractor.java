package persianutils.date;

import java.util.Map;

public interface DateExtractor<Pattern> {
  Map<DateTimeField, Integer> extract(String input, Pattern pattern);
}
