/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */

package com.ip.itest.pattern.bridge;

import com.ip.itest.pattern.bridge.BackendImpl;

abstract class App {
	protected BackendImpl back;

	public BackendImpl getBack() {
		return back;
	}

	public void setBack(BackendImpl back) {
		this.back = back;
	}

	abstract public void save();

	abstract public void delete();

}
