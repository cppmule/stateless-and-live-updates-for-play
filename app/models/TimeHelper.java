package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHelper {
	
	private static final String DATE_FORMAT = "HH:mm:ss";
	
	public static String getCurrentTime(){
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		return dateFormat.format(new Date());
	}

}
