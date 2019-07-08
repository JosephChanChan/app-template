package com.byb.service.impl.system;

import com.byb.dao.system.PermissionMapper;
import com.byb.model.entity.system.Permission;
import com.byb.service.impl.BaseServiceImpl;
import com.byb.service.system.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Autowired
    private PermissionMapper permissionMapper;
}
