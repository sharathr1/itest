/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */

package com.ip.itest.pattern.bridge;

public class Database extends BackendImpl {



	public Database(Organization org) {
		super(org);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save() {
		org.save();
		System.out.println("Saved in DB");
	}

	@Override
	public void delete() {
		System.out.println("Deleted in DB");

	}

}
