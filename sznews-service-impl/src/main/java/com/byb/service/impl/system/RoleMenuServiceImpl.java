package com.byb.service.impl.system;

import com.byb.model.entity.system.RoleMenu;
import com.byb.dao.system.RoleMenuMapper;
import com.byb.service.impl.BaseServiceImpl;
import com.byb.service.system.RoleMenuService;
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
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
    @Autowired
    private RoleMenuMapper roleMenuMapper;
}
