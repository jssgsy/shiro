package com.univ.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * created by Univ
 * 16/6/16 14:00
 * 自定义realm实现。一个realm就是一个数据源,即是说,用户名密码需要在这里(就像数据库中存的用户名密码)
 * 自定义realm 的步骤:
 * 1. 实现Realm接口;
 * 2. 在ini文件中指定此realm为securityManager需要用的realm
 */
public class SingleRealm implements Realm{


    /**
     * 返回此realm的名字,自定义
     * @return
     */
    @Override
    public String getName() {
        return "univSingleRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();

        /**
         * 注意这里的写法,不能直接写成String password = (String)token.getCredentials();
         * password需要用char[]类型
         */
        String password = new String((char[])token.getCredentials());

        if (!"univ".equals(username)) {
            throw new UnknownAccountException("用户名错误");
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException("密码错误");
        }

        return new SimpleAuthenticationInfo(username, password,getName());
    }
}
