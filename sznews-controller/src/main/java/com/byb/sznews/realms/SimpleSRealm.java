package com.byb.sznews.realms;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.byb.framework.utils.encoder.PasswordUtils;
import com.byb.framework.utils.stomp.CollectionsKit;
import com.byb.model.entity.system.Permission;
import com.byb.model.entity.system.Role;
import com.byb.model.entity.system.User;
import com.byb.service.system.PermissionService;
import com.byb.service.system.RoleService;
import com.byb.service.system.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author Joseph
 * @since 2019/7/6 11:40
 */
public class SimpleSRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User principal = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        List<Role> roles = roleService.selectBy(principal.getId());
        Set<String> roleIdSet = roles.stream().map(role -> String.valueOf(role.getId())).collect(Collectors.toSet());
        authorizationInfo.addRoles(roleIdSet);

        roles.forEach(role -> {
            List<Permission> permissions = permissionService.selectBy(role.getId());
            if (CollectionsKit.nonNullAndEmpty(permissions)) {
                Set<org.apache.shiro.authz.Permission> customPSet = new HashSet<>();
                for (Permission customP : permissions) {
                    customPSet.add(new CustomPermission(customP));
                }
                authorizationInfo.addObjectPermissions(customPSet);
            }
        });

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        User user = userService.selectBy(principal);
        if (null == user) {
            throw new AccountException("帐号或密码不正确！");
        }
        SimplePrincipalCollection principalCollection = new SimplePrincipalCollection();
        principalCollection.add(user, "simpleSRealm");
        return new SimpleAuthenticationInfo(principalCollection, user.getPassword());
    }

    /**
     * 清空当前用户权限信息
     */
    public  void clearCachedAuthorizationInfo() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
    /**
     * 指定principalCollection 清除
     */
    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
}
