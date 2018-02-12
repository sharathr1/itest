/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 */
package com.ip.itest.mail;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * MailProperties POJO
 * 
 * @author 999951
 * @return String (mail will be sent to recipient )
 */
@Getter
@Setter
public class MailProperties {

    @NotNull
    private int mailID;
    private String recipient;
    private String mailfrom;
    ArrayList<String> mailTo1;
    @NotNull
    private String mailto;
    private String mailcc;
    private String subject;
    private String message;
    private String signature;
    private String time;
    private String date;
    private String contatctDetails;
    private String status;


   

}
