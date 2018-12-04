package com.wondertek.baiying.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;

public class TimeTest {
	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = simpleDateFormat.format(new Date());
		System.out.println(s);
		try {
			Date parse = simpleDateFormat.parse("2017-09-06 15:18:40");
			System.out.println(parse.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(new Date());
		ZonedDateTime now= ZonedDateTime.now();
		System.out.println(now.getZone());
		System.out.println(now);
	}
}
