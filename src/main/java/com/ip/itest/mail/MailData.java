/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip.itest.mail;

import javax.validation.constraints.NotNull;

public class MailData {
    private String from;
    @NotNull
    private String to;
    private String subject;
    private String body;
    private String signature;
    
    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public String getFrom() {
	return from;
    }

    public void setFrom(String from) {
	this.from = from;
    }

    public String getTo() {
	return to;
    }

    public void setTo(String to) {
	this.to = to;
    }

    public String getBody() {
	return body;
    }

    public void setBody(String body) {
	this.body = body;
    }

    public String getSignature() {
	return signature;
    }

    public void setSignature(String signature) {
	this.signature = signature;
    }

	
    
}
