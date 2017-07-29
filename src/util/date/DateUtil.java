package util.date;

import java.text.DecimalFormat;
import java.util.Calendar;

public class DateUtil {
	public static String getToDate() {
		DecimalFormat df = new DecimalFormat("00");
		Calendar currentCal = Calendar.getInstance();

		currentCal.add(currentCal.DATE, 0);

		String year = Integer.toString(currentCal.get(Calendar.YEAR));
		String month = df.format(currentCal.get(Calendar.MONTH) + 1);
		String day = df.format(currentCal.get(Calendar.DAY_OF_MONTH));

		return year + "-" + month + "-" + day;
	}
}
