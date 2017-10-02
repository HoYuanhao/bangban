package com.xunyanhui.security.realm;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.springframework.beans.factory.annotation.Autowired;

import com.xunyanhui.exception.DaoException;

import com.xunyanhui.service.UserService;

public class LoginRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addRole("user");
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal(); // 得到用户名
		String password = new String((char[]) token.getCredentials()); // 得到密码
		System.out.println(username + "=======" + password);
		int result = userService.login(username, password);
		SimpleAuthenticationInfo info = null;

		switch (result) {
		case UserService.LOGIN_USER_NAME_FAIL:
			throw new UnknownAccountException();
		case UserService.LOGIN_PASSWORD_FAIL:
			throw new IncorrectCredentialsException();
		case UserService.LOGIN_ERRO:
			throw new DaoException();
		case UserService.LOGIN_SUCCESS:
			info = login(username, password);
			
			break;
		}

		// 如果身份认证验证成功，返回一个AuthenticationInfo实现；
		return info;
	}

	private SimpleAuthenticationInfo login(String username, String password) {
		// TODO Auto-generated method stub
		return new SimpleAuthenticationInfo(username, password, getName());
	}
}
