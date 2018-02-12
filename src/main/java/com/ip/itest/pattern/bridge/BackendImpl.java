/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */

package com.ip.itest.pattern.bridge;

public abstract class BackendImpl {

	protected Organization org;
	//Set Org at Constructor
	public BackendImpl(Organization org) {
		this.org = org;
	}

	abstract void save();
	abstract void delete();
}
