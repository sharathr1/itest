package com.ip.itest.predixuaa.service;

import org.springframework.security.oauth2.provider.OAuth2Authentication;

import com.ip.itest.predixuaa.model.UserInfo;

public interface UserInfoService {
	UserInfo getUserInfo(OAuth2Authentication activeUser) throws Exception;
}
