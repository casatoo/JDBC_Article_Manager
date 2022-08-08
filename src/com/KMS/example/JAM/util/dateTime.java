package com.KMS.example.JAM.util;
import java.util.Date;
import java.text.SimpleDateFormat;

public class dateTime {
	
	public String dateTime(){
		Date DT = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd hh:mm:ss"); 
		String strNowDate = simpleDateFormat.format(DT);
		return strNowDate;
	}
}

