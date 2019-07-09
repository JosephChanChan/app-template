package com.byb.model.page.system;

import java.sql.Timestamp;
import java.util.Date;

import com.byb.model.entity.system.Permission;
import lombok.Data;

/**
 * <p>
 * 接口权限。

 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
@Data
public class PermissionPageForm extends PageForm<Permission>{

    private String keyword;

    private Integer roleId;
}
