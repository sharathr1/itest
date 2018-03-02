package com.ip.itest.utils;

import java.util.HashMap;
import java.util.Map;

public enum Day {
	 SUNDAY(0), 
     MONDAY(1), 
     TUESDAY(2), 
     WEDNESDAY(3), 
     THURSDAY(4), 
     FRIDAY(5), 
     SATURDAY(6);
 
     private static final Map lookup = 
			new HashMap();
     static {
         //Create reverse lookup hash map 
         for(Day d : Day.values())
             lookup.put(d.getDayValue(), d);
     }
     private int dayValue;
     private Day(int dayValue) {
          this.dayValue = dayValue;
     }
     public int getDayValue() { return dayValue; }
     public static Day get(int dayValue) { 
        //the reverse lookup by simply getting 
        //the value from the lookup HsahMap. 
          return (Day) lookup.get(dayValue); 
     }
}
