package com.byb.service.impl.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.byb.dao.system.RoleMapper;
import com.byb.dao.system.RolePermissionMapper;
import com.byb.dao.system.UserMapper;
import com.byb.dao.system.UserRoleMapper;
import com.byb.framework.utils.stomp.CollectionsKit;
import com.byb.model.entity.system.Role;
import com.byb.model.entity.system.RolePermission;
import com.byb.model.entity.system.UserRole;
import com.byb.model.page.system.RolePageForm;
import com.byb.model.vo.system.UserVo;
import com.byb.service.impl.BaseServiceImpl;
import com.byb.service.system.RoleService;
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
    public IPage<UserVo> relationUserList(Integer roleId, RolePageForm pageForm) {
        return userMapper.relationUserList(pageForm, roleId);
    }

    @Override
    public IPage<UserVo> highLightUserByRole(Integer roleId, RolePageForm pageForm) {
        return userMapper.highLightUserByRole(pageForm, roleId);
    }

    @Override
    public List<Role> selectBy(Integer userId) {
        return roleMapper.selectByUserId(userId);
    }
}
