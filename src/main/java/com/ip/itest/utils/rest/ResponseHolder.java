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
* File Name: ResponseHolder.java
* Date: Dec 13, 2016
* Created By: 212398958
* 
*/
package com.ip.itest.utils.rest;

/**
 * @author 212398958
 *
 */
public class ResponseHolder<T, E> {

	public ResponseHolder() {

	}

	/**
	 * @param response
	 * @param error
	 */
	public ResponseHolder(T response, E error) {
		super();
		this.response = response;
		this.error = error;
	}

	private T response;
	private E error;

	/**
	 * @return the response
	 */
	public T getResponse() {
		return response;
	}

	/**
	 * @param response
	 *            the response to set
	 */
	public void setResponse(T response) {
		this.response = response;
	}

	/**
	 * @return the error
	 */
	public E getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(E error) {
		this.error = error;
	}

}
