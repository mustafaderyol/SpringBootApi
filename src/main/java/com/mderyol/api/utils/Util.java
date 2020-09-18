package com.mderyol.api.utils;

import java.util.Date;

public class Util {
	public static Long getUUID() {
		Date date = new Date(System.currentTimeMillis());
		return date.getTime();
	}
}
