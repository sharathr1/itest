/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip.itest.pattern.clone;

import java.util.ArrayList;
import java.util.List;

public class Bookshop implements Cloneable {
	private String shopname;
	private List<Book> books = new ArrayList<>();

	public Bookshop() {
		// TODO Auto-generated constructor stub
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void load() {
		for (int i = 0; i < 10; i++) {
			Book b = new Book();
			b.setName("test_" + i);
			b.setPrice(10);
			getBooks().add(b);
		}
	}

	@Override
	public String toString() {
		System.out.println("Bookshop [shopname=" + shopname + ", books=" + books + "]");
		return "Bookshop [shopname=" + shopname + ", books=" + books + "]";
	}

	@Override
	protected Bookshop clone() throws CloneNotSupportedException {
		Bookshop bshop = new Bookshop();
		for (Book b : this.getBooks()) {
			bshop.getBooks().add(b);
		}
		return bshop;
	}

	// @Override -- Shallow Copy
	// protected Object clone() throws CloneNotSupportedException {
	// return super.clone();
	// }
}
