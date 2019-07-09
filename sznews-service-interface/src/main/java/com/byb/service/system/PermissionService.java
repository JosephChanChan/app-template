package com.byb.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.byb.model.entity.system.Permission;
import com.byb.model.page.system.PermissionPageForm;
import com.byb.service.BaseService;

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


    IPage highLightPermissionByRole(Integer roleId, PermissionPageForm pageForm);

    void assignPermission(Integer roleId, List<Integer> permissionIds);

    List<Permission> selectBy(Integer roleId);
}
