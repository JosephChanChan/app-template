package com.byb.service.impl.system;

import com.byb.dao.system.RoleMapper;
import com.byb.model.entity.system.Role;
import com.byb.service.impl.BaseServiceImpl;
import com.byb.service.system.RoleService;
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
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
}
