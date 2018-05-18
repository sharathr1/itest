/*
* Copyright (c) 2016 General Electric Company. All rights reserved.
*
* The copyright to the computer software herein is the property of
* General Electric Company. The software may be used and/or copied only
* with the written permission of General Electric Company or in accordance
* with the terms and conditions stipulated in the agreement/contract
* under which the software has been supplied.
* 
* Project Name: gridfit-mobile-microservice
* File Name: PMError.java
* Date: Dec 13, 2016
* Created By: 212398958
* 
*/
package com.ip.itest.utils.rest;

/**
 * @author 212398958
 *
 */
public class PMError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errorMessage;
	private String errorCode;

	public PMError(String errorMessage, String errorCode) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
