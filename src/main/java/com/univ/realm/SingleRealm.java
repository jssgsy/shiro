package com.univ.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * created by Univ
 * 16/6/16 14:00
 * 1. 自定义realm实现。一个realm就是一个数据源,即是说,用户名密码需要在这里(就像数据库中存的用户名密码)
 * 2. 自定义realm 的步骤:
     * 1. 实现Realm接口,一般继承AuthorizingRealm即可,其继承了AuthenticatingRealm类;
     * 2. 在ini文件中指定此realm为securityManager需要用的realm
 * 3. 自定义的Realm如果直接实现Realm接口,则不提供授权操作。
 *      参考ModularRealmAuthorizer.java中的hasRole方法
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
     * 认证在这里进行(直接实现Realm接口的Realm没有授权功能)
     */
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //实例代码可参看RealmDemo1的doGetAuthenticationInfo方法
        return null;
    }
}
