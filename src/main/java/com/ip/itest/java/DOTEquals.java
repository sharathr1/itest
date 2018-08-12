package com.ip.itest.java;

public class DOTEquals {

	public static void main(String[] args) {

		String s1 = new String("HELLO");
		String s2 = new String("HELLO");
		String s3 = "SR";
		String s4 = "SR";
		System.out.println("--------------");

		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());

		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));

		System.out.println("--------------");
		System.out.println(s3.hashCode());
		System.out.println(s4.hashCode());
		System.out.println(s3 == s4);
		System.out.println(s3.equals(s4));

		System.out.println("--------------");
		Emp e1 = new Emp();
		e1.id=1;
		Emp e2 = new Emp();
		e2.id=1;
		Emp e3=e2;
		e3.id=2;
		System.out.println(e1.hashCode());
		System.out.println(e2.hashCode());
		System.out.println(e3.hashCode());
		System.out.println(e1 == e2);
		System.out.println(e1.equals(e2));
		System.out.println("--------------");

		System.out.println(e3 == e2);
		System.out.println(e3.equals(e2));
	}

}

class Emp {
	int id;
	String name;
}