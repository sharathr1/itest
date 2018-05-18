package com.ip.itest.java.java8;

public class NestedFunctions {
	static java.util.function.BiConsumer<Integer, Integer> times = (i, num) -> {
	    i *= num;
	    System.out.println(i);
	};
	public static void main(String[] args) {
	
		
		for (int i = 1; i < 1; i++) {
		    times.accept(i, 2); //multiply i by 2 and print i
		    times.accept(i, i); //square i and then print the result
		}
	}

}
