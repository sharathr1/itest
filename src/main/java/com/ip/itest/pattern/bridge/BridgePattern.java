/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */

package com.ip.itest.pattern.bridge;
/**
 * Allow us to develop Impl Class independently . 
 *  Ex: 1level >> File / DB  
 * 	    2level >> Manager /Employee
 */
public class BridgePattern {
	public static void main(String[] args) {
		BackendImpl kcBackendImpl = new Database(new Manager());
		kcBackendImpl.save();
		
		BackendImpl kcBackendImpl2 = new FileSystem(new Manager());
		kcBackendImpl2.save();
		
	}
}
