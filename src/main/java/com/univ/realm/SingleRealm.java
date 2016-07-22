package com.univ.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * created by Univ
 * 16/6/16 14:00
 * 自定义realm实现。一个realm就是一个数据源,即是说,用户名密码需要在这里(就像数据库中存的用户名密码)
 * 自定义realm 的步骤:
 * 1. 实现Realm接口,一般继承AuthorizingRealm即可,其继承了AuthenticatingRealm类;
 * 2. 在ini文件中指定此realm为securityManager需要用的realm
 */
public class SingleRealm implements Realm{


    /**
     * 返回此realm的名字,自定义,每个realm的名字必须唯一
     * @return
     */
    @Override
    public String getName() {
        return "univSingleRealm";
    }

    /**
     * 判断是否接受此token类型的认证,返回false则不会调用下面的getAuthenticationInfo方法
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        //如果传进来的token是UsernamePasswordToken或者其子类型,则接受认证,认证在getAuthenticationInfo方法中。
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 认证和授权应该都在这里进行
     */
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();

        /**
         * 注意这里的写法,不能直接写成String password = (String)token.getCredentials();
         * password在UsernamePasswordToken中是char[]类型
         */
        String password = new String((char[])token.getCredentials());

        /**
         * 这里是直接将用户密码硬编码在这里,如果是存放在数据库中,则需要从数据库取出,然后比较
         */
        if (!"univ".equals(username)) {
            throw new UnknownAccountException("用户名错误");
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException("密码错误");
        }

        return new SimpleAuthenticationInfo(username, password,getName());
    }
}
