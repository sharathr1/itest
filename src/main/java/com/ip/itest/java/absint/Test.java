package com.ip.itest.java.absint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class Test {
	public static void main(String[] args) {

		TestEmp t = new TestEmp();

		t.setEmpname("R");
		t.setEmpId("999");

		Consumer<String> c = System.out::println;
		System.out.println("______________________________");

		List<Integer> list = Arrays.asList(12,5,45,18,40,24,33);

		// Using an anonymous class
//		Numbers.findNumbers(list, new BiPredicate<Integer, Integer>() {
//			  public boolean test(Integer i1, Integer i2) {
//			    return  Numbers.isMoreThanFifty(i1, i2);
//			  }
//			});

//		Numbers.findNumbers(list, (i1, i2) -> Numbers.isMoreThanFifty(i1, i2));
//		Numbers.findNumbers(list, Numbers::isMoreThanFifty).toString();
		System.out.println(Numbers.findNumbers(list, Numbers::isMoreThanFifty).toString());
		list.forEach(System.out::println);

		//changename(t);

	}



	private static void changename(TestEmp r) {

		r = new TestEmp();
		r.setEmpname("SR");
		System.out.println(r.getEmpname());
		System.out.println(r.getEmpId());
	}
}

class Numbers {
	  public static boolean isMoreThanFifty(int n1, int n2) {
		  System.out.println(n1+"  ::  "+n2);
	    return (n1 + n2) > 50;
	  }
	  

	  public static List<Integer> findNumbers(
	    List<Integer> l, BiPredicate<Integer, Integer> p) {
	      List<Integer> newList = new ArrayList<>();
	      for(Integer i : l) {
	        if(p.test(i, i + 10)) {
	          newList.add(i);
	        }
	      }
	      return newList;
	  }
	}