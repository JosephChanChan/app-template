package com.byb.service.impl.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.byb.dao.system.PermissionMapper;
import com.byb.dao.system.RolePermissionMapper;
import com.byb.framework.utils.stomp.CollectionsKit;
import com.byb.model.entity.system.Permission;
import com.byb.model.entity.system.RolePermission;
import com.byb.model.page.system.PermissionPageForm;
import com.byb.service.impl.BaseServiceImpl;
import com.byb.service.system.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 接口权限。
 服务实现类
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper, Permission> implements PermissionService {

    private Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public IPage highLightPermissionByRole(Integer roleId, PermissionPageForm pageForm) {
        return permissionMapper.highLightPermissionByRole(pageForm, roleId);
    }


    @Override
    public void assignPermission(Integer roleId, List<Integer> permissionIds) {
        QueryWrapper<RolePermission> rolePermissionQueryWrapper = new QueryWrapper<>();
        rolePermissionQueryWrapper.eq("role_id", roleId);
        int delete = rolePermissionMapper.delete(rolePermissionQueryWrapper);
        logger.info("assignPermission roleId {} delete RolePermission count {}", roleId, delete);
        if (CollectionsKit.nonNullAndEmpty(permissionIds)) {
            int count = rolePermissionMapper.insertRolePermission(roleId, permissionIds);
            logger.info("assignPermission roleId {} insert RolePermission count {}", roleId, count);
        }
    }

    @Override
    public List<Permission> selectBy(Integer roleId) {
        return permissionMapper.selectByRoleId(roleId);
    }
}
