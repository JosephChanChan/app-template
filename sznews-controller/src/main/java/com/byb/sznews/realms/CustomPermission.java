package com.byb.sznews.realms;

import lombok.Data;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * @author Joseph
 * @since 2019/7/8 18:29
 */
@Data
public class CustomPermission implements Permission {

    private String url;

    public CustomPermission(com.byb.model.entity.system.Permission permission) {
        this.url = permission.getUrl().toLowerCase();
    }

    @Override
    public boolean implies(Permission p) {
        WildcardPermission wildcardPermission = (WildcardPermission) p;
        return wildcardPermission.toString().equals(this.url);
    }

}

