package util;

import java.util.Calendar;
import java.util.Date;

public class Manager {
	
	public static void start() {
		
	}

	public static Date getTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND,0);
		return cal.getTime();
	}
}
