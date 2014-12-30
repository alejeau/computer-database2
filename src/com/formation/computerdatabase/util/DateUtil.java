package com.formation.computerdatabase.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.formation.computerdatabase.exception.DateFormatException;

public class DateUtil {

	/*--------------------------------------------------------------
	 * Static Attributes
	 --------------------------------------------------------------*/
	public static final String DATE_FORMAT = "dd-mm-YYYY";
	

	
	public static Date stringToDate(String stringDate) {
		Date date;
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		try {
			date = df.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new DateFormatException("Wrong date format was provided.", e);
		}
		return date;
	}

}
