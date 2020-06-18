package com.joseph.template.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.joseph.template.model.entity.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
public interface RoleService extends BaseService<Role> {

    boolean removeRole(Integer roleId);

    void assignUser(Integer roleId, List<Integer> selectedUserIds);

    List<Role> selectBy(Integer userId);
}
