package com.joseph.template.service.impl;

import com.joseph.template.model.entity.RolePermission;
import com.joseph.template.dao.RolePermissionMapper;
import com.joseph.template.service.RolePermissionService;
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
