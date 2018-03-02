package com.ip.itest.utils;

import java.util.HashMap;
import java.util.Map;

public enum DayString {
	SUNDAY("sun"), MONDAY("mon"), TUESDAY("tue"), WEDNESDAY("wed"), THURSDAY("thur"), FRIDAY("fri"), SATURDAY("sat");

	private static final Map lookup = new HashMap();
	static {
		// Create reverse lookup hash map
		for (DayString d : DayString.values())
			lookup.put(d.getDayValue(), d);
	}
	private String dayValue;

	private DayString(String dayValue) {
		this.dayValue = dayValue;
	}

	public String getDayValue() {
		return dayValue;
	}

	public static DayString get(String dayValue) {
		// the reverse lookup by simply getting
		// the value from the lookup HsahMap.
		return (DayString) lookup.get(dayValue);
	}
}
