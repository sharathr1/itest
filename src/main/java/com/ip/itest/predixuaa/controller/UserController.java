package com.ip.itest.predixuaa.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ip.itest.predixuaa.model.UserInfo;
import com.ip.itest.predixuaa.service.UserInfoService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * UserController
 * 
 * @author "Andy Wickersham (212425740)"
 *
 */
@RestController
@Slf4j
public class UserController {
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * Retrieve user details
	 * 
	 * @param activeUser
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserInfo getUserInfo(final @AuthenticationPrincipal OAuth2Authentication activeUser) throws Exception {
		log.info("User Info");
		return userInfoService.getUserInfo(activeUser);
	}
	 
	@ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        List<ObjectError> validationErrors = bindingResult.getAllErrors();

        return ErrorResponse.fromValidationErrors(validationErrors);
    }

}
