package com.xunyanhui.security.realm;

import org.apache.shiro.authc.AuthenticationToken;

import java.util.Map;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-26
 * <p>Version: 1.0
 * principals：身份，即主体的标识属性，可以是任何东西，如用户名、邮箱等，唯一即可。
 * 一个主体可以有多个principals，但只有一个Primary principals，一般是用户名/密码/手机号。
 * credentials：证明/凭证，即只有主体知道的安全值，如密码/数字证书等。
 * 最常见的principals和credentials组合就是用户名/密码了。接下来先进行一个基本的身份认证。
 */
public class StatelessToken implements AuthenticationToken {

    private String username;
    private Map<String, ?> params;
    private String clientDigest;

    public StatelessToken(String username,  Map<String, ?> params, String clientDigest) {
        this.username = username;
        this.params = params;
        this.clientDigest = clientDigest;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public  Map<String, ?> getParams() {
        return params;
    }

    public void setParams( Map<String, ?> params) {
        this.params = params;
    }

    public String getClientDigest() {
        return clientDigest;
    }

    public void setClientDigest(String clientDigest) {
        this.clientDigest = clientDigest;
    }

    @Override
    public Object getPrincipal() {
       return username;
    }

    @Override
    public Object getCredentials() {
        return clientDigest;
    }
}
