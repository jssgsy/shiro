package com.univ.realm;

import org.apache.shiro.authc.*;
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
