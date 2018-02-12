/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip.itest.pattern.clone;

/**
 * 
 * @author 999951 ; Protype Desgin Pattern by Cloning
 *         https://dzone.com/articles/java-copy-shallow-vs-deep-in-which-you-will-swim
 */
public class CloneDemo {
	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Bookshop bs = new Bookshop();
		bs.setShopname("SRK");
		bs.load();
		bs.toString();

		// Bookshop bs2 = bs; // -- Shallow copy
		Bookshop bs2 = bs.clone(); // Deep Copy
		bs.getBooks().remove(9);

		bs2.setShopname("RR");
		bs2.toString();
		bs.toString();

	}

}
