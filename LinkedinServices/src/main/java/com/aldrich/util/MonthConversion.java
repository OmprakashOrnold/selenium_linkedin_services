package com.aldrich.util;

public class MonthConversion 
{
	public int StringToInt(String month)
	{
		int mon=0;
		try
		{
			if(month.trim().equals("January"))
				mon=1;
			else if(month.trim().equals("February"))
				mon=2;
			else if(month.trim().equals("March"))
				mon=3;
			else if(month.trim().equals("April"))
				mon=4;
			else if(month.trim().equals("May"))
				mon=5;
			else if(month.trim().equals("June"))
				mon=6;
			else if(month.trim().equals("July"))
				mon=7;
			else if(month.trim().equals("August"))
				mon=8;
			else if(month.trim().equals("September"))
				mon=9;
			else if(month.trim().equals("October"))
				mon=10;
			else if(month.trim().equals("November"))
				mon=11;
			else if(month.trim().equals("December"))
				mon=12;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return mon;
	}

}
