/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip.itest.mail;

import org.json.JSONException;
import org.json.JSONObject;

public interface IMailService {

    String mailWithAttachment(MailProperties mail) throws Exception;

    JSONObject mailsender(MailProperties mail) throws JSONException;
     
   
}
