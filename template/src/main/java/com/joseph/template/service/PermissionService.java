package com.joseph.template.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.joseph.template.model.entity.Permission;

import java.util.List;

/**
 * <p>
 * 接口权限。
 服务类
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
public interface PermissionService extends BaseService<Permission> {


    void assignPermission(Integer roleId, List<Integer> permissionIds);

    List<Permission> selectBy(Integer roleId);
}
