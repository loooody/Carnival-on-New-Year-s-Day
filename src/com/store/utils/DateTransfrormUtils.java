package com.store.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTransfrormUtils {
	//String to Date
public Date String2Date(String StrDate){
	Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	try {
		date=sdf.parse(StrDate);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return date;
	
}
 //Date to String
public String Date2String(){
	return null;
	
}
}
