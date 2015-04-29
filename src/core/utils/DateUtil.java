package core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	public static String getDateString(int i) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, i);    
        return dateFormat.format(cal.getTime());
	}	
}
