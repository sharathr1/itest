package com.ip.itest.java.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Java8Streams {
	public static void main(String[] args) {
		long sentMillis = System.currentTimeMillis();

		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader("c:\\Users\\999951.TCSGEGDC\\Desktop\\pmdocs.json"));
			ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
			// convert Object to JSONObject
			JSONObject jsonObject = (JSONObject) object;
		
			PMDocuments pm = objectMapper.readValue(jsonObject.toJSONString(), PMDocuments.class);
			List<PMRows> pmrows = new ArrayList<>();
			pmrows = pm.getRows();
			List<PMRows> resPmrows = new ArrayList<>();
			resPmrows = pmrows.stream().map(i -> {
				if (i.getDoc().getStatus() != null) {
					if (i.getDoc().getStatus().equalsIgnoreCase("Created")) {
						return i;
					}
				}
				return null;
			}).collect(Collectors.toList());
			while (resPmrows.remove(null));
				
			resPmrows.stream().forEach(System.out::println);
			System.out.println("PM Document :"+resPmrows.size());
			System.out.println("Total :"+pm.getTotal_rows());
			long receivedMillis = System.currentTimeMillis();
			long ping = receivedMillis - sentMillis;
			System.out.println(ping);
			
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

// Reading the String
// JSONArray rows = (JSONArray) jsonObject.get("rows");
// Long total_rows = (Long) jsonObject.get("total_rows");
// Long update_seq = (Long) jsonObject.get("update_seq");
// System.out.println(total_rows + " count " + update_seq);
//// 31820
//// //Reading the array
// JSONArray countries = (JSONArray) jsonObject.get("rows");

//
// //Printing all the values
// System.out.println("Name: " + name);
// System.out.println("Age: " + age);
// System.out.println("Countries:");
// for(Object country : countries)
// {
// System.out.println("\t"+country.toString());
// }