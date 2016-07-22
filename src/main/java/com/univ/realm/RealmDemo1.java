package com.univ.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * created by Univ
 * 16/6/17 10:06
 */

/**
 * 1. AuthorizingRealm继承自AuthenticatingRealm,后者又继承自CachingRealm,后者实现了Realm;
 * 2. 使用自定义realm的方法是在在ini文件中的[main]下将此realm赋值给SecurityManager:
 *      realmDemo1 = com.univ.realm.RealmDemo1;
 *      securityManager.realms=$realmDemo1(可以省略,会自动扫描)
 */
public class RealmDemo1 extends AuthorizingRealm{


    /**
     * 是AuthorizingRealm中的方法,在这里完成授权的操作
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 是AuthenticatingRealm中的方法,在这里完成认证的操作
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return null;
    }
}
