package com.joseph.template.dao;

import com.joseph.template.model.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
@Repository
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    int insertRolePermission(@Param("roleId") Integer roleId, @Param("permissionIds") List<Integer> permissionIds);

}
