package com.byb.dao.system;

import com.byb.model.entity.system.RolePermission;
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
