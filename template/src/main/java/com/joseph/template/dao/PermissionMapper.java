package com.joseph.template.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.joseph.template.model.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 接口权限。
 Mapper 接口
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {


    List<Permission> selectByRoleId(@Param("roleId") Integer roleId);

}
