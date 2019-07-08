package com.byb.sznews.realms;

import com.byb.framework.utils.encoder.PasswordUtils;
import com.byb.model.entity.system.User;
import com.byb.service.system.PermissionService;
import com.byb.service.system.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Joseph
 * @since 2019/7/6 11:40
 */
public class SimpleSRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roleCode = new TreeSet<>();
        roleCode.add("admin");
        Set<String> permissionUrl = new TreeSet<>();
        permissionUrl.add("auth/register");
        authorizationInfo.addRoles(roleCode);
        authorizationInfo.addStringPermissions(permissionUrl);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        User user = userService.selectBy(principal);
        if (null == user) {
            throw new AccountException("帐号或密码不正确！");
        }
        // 这里自己做校验，不再交给 Shiro 去做密码校验
        // 这里的密码应该是前端传递时已经过一次 md5 加密（要保证传输中，明文加密成密文）
        String credentials = (String) token.getCredentials();
        // MD5( MD5(明文密码), 盐 )
        String encrypt = PasswordUtils.encrypt(credentials, user.getSalt());
        if (!user.getPassword().equals(encrypt)) {
            throw new AccountException("帐号或密码不正确！");
        }
        // 通过验证，这里未来可做扩展的业务操作，用户禁用，更新用户登录时间等..

        // 保存session信息
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSession", user);
        return new SimpleAuthenticationInfo(user, user.getPassword(), user.getSalt());
    }
}
