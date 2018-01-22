package com.ip.itest.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
	public static void main(String[] args) {
		String[] a = { "abbc", "dbbdd" };
		String[] b = { "abbc", "ddbab" };
		String[] intArray = { "1", "2" };

		List<String> li = new ArrayList<>();
		List<String> res = new ArrayList<>();
		System.out.println("Array to List");

		li = Arrays.asList(a);
		li.stream().forEach(System.out::print);
		System.out.println();

		System.out.println("MAP to a Function");
		// map will stream with required/defined modification
		res = li.stream().map(i -> {
			if (i.startsWith("a")) {
				return i.toUpperCase();
			}
			return null;
		}).collect(Collectors.toList());
		res.stream().forEach(System.out::println);

		System.out.println("Filter");
		// filter function can return only true or false
		li.stream().filter(i -> i.startsWith("a")).forEach(p -> System.out.print(p + " "));

		System.out.println();
		System.out.println("List to Array");
		String[] strArray = li.stream().toArray(String[]::new);
		System.out.println(Arrays.asList(strArray));

		Map<String, String> intMap = li.stream().collect(Collectors.toMap(i -> i, i -> i + "add"));
		//li.stream().sorted().collect(Collectors.toList());
		//list.sort(Comparator.comparing(AnObject::getAttr));

		System.out.println(intMap);

		System.out.println("Parallel Stream");
		List<Integer> ss = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
		List<Integer> result = new ArrayList<Integer>();
		ss.parallelStream().map(s -> {
			synchronized (result) {
				if (result.size() < 10) {
					result.add(s);
				}
			}
			return s;
		}).forEach(e -> {
		});
		// Stream Contains 50 ??        .allMatch(i -> i<10))  .noneMatch()  .anyMatch() .count()
		System.out.println("Stream Contains 50 ?? : "+ss.stream().noneMatch(i -> i == 50));
		System.out.println(result);
	}
}
