package com.univ;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * created by Univ
 * 16/5/22 18:56
 */

/**
 * 目前只是完成了简单认证,还没有授权
 */
public class ShiroTest {

    @Test
    public void test(){
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("univ", "123");

        try {
            //4、登录，即身份验证
            subject.login(token);
            System.out.println("........登录成功........");
            if (subject.hasRole("admin")) {
                System.out.println("当前用户具有admin角色");
            }else{
                System.out.println("当前用户不具有admin角色");
            }
        } catch (AuthenticationException e) {
            //5、身份验证失败
        }

        assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();
    }

}
