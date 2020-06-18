package com.joseph.template.service.impl;

import com.joseph.template.model.entity.RoleMenu;
import com.joseph.template.dao.RoleMenuMapper;
import com.joseph.template.service.RoleMenuService;
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
public class RoleMenuServiceImpl
        extends BaseServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;


}
