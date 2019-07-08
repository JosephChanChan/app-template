package com.byb.service.impl.system;

import com.byb.model.entity.system.RolePermission;
import com.byb.dao.system.RolePermissionMapper;
import com.byb.service.system.RolePermissionService;
import com.byb.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
}
