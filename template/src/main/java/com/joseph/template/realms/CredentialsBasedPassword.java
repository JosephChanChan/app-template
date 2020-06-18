package com.joseph.template.realms;

import com.joseph.framework.utils.encoder.PasswordUtils;
import com.joseph.template.model.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author Joseph
 * @since 2019/7/8 11:36
 */
public class CredentialsBasedPassword implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken submittedToken, AuthenticationInfo dataSourceInfo) {
        PrincipalCollection principals = dataSourceInfo.getPrincipals();
        User primaryPrincipal = (User) principals.getPrimaryPrincipal();

        // 这里自己做校验，不再交给 Shiro 去做密码校验
        // 这里的密码应该是前端传递时已经过一次 md5 加密（要保证传输中，明文加密成密文）
        String md5ed = new String((char[]) submittedToken.getCredentials());
        // MD5( MD5(明文密码), 盐 )
        String expectedCredentials = PasswordUtils.encrypt(md5ed, primaryPrincipal.getSalt());
        if (!primaryPrincipal.getPassword().equals(expectedCredentials)) {
            throw new AccountException("帐号或密码不正确！");
        }

        // 通过验证，这里未来可做扩展的业务操作，用户禁用，更新用户登录时间等..

        // 保存session信息
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSession", primaryPrincipal);
        return true;
    }
}
