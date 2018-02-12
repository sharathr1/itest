/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip.itest.pattern.bridge;

public class FileSystem extends BackendImpl{

	public FileSystem(Organization org) {
		super(org);
	}

	@Override
	public void save() {
		org.save();
		System.out.println("Saved in Files");		
		
	}

	@Override
	public void delete() {
		System.out.println("Deleted in Files");		
	}

}
