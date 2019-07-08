package com.byb.service.impl.system;

import com.byb.model.entity.system.UserRole;
import com.byb.dao.system.UserRoleMapper;
import com.byb.service.system.UserRoleService;
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
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;
}
