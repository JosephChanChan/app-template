package com.joseph.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joseph.template.dao.RoleMapper;
import com.joseph.template.dao.RolePermissionMapper;
import com.joseph.template.dao.UserMapper;
import com.joseph.template.dao.UserRoleMapper;
import com.joseph.framework.utils.stomp.CollectionsKit;
import com.joseph.template.model.entity.Role;
import com.joseph.template.model.entity.RolePermission;
import com.joseph.template.model.entity.UserRole;
import com.joseph.template.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    @Transactional
    public boolean removeRole(Integer roleId) {
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("id", roleId);
        int delete = roleMapper.delete(roleQueryWrapper);
        if (delete > 0) {
            QueryWrapper<RolePermission> rolePermissionQueryWrapper = new QueryWrapper<>();
            delete = rolePermissionMapper.delete(rolePermissionQueryWrapper);
            logger.info("removeRole() roleId {}, delete RolePermission count {}", roleId, delete);
        }
        return true;
    }


    @Override
    @Transactional
    public void assignUser(Integer roleId, List<Integer> selectedUserIds) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        int delete = userRoleMapper.delete(queryWrapper);
        logger.info("assignUser roleId {} delete UserRole count {}", roleId, delete);
        if (CollectionsKit.nonNullAndEmpty(selectedUserIds)) {
            int inserted = userRoleMapper.insertRoleUser(roleId, selectedUserIds);
            logger.info("assignUser roleId {} insert UserRole count {}", roleId, inserted);
        }
    }

    @Override
    public List<Role> selectBy(Integer userId) {
        return roleMapper.selectByUserId(userId);
    }
}
