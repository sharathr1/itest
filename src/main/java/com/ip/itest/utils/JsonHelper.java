/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip.itest.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {
	
	public static String toJsonString(Object obj) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		}catch(Exception ex) {
			return "";
		}
	}
}
