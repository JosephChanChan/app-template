package com.byb.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.byb.model.entity.system.Permission;
import com.byb.model.page.system.PermissionPageForm;
import com.byb.model.vo.system.PermissionVo;
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

    IPage<PermissionVo> highLightPermissionByRole(PermissionPageForm pageForm, @Param("roleId") Integer roleId);

    List<Permission> selectByRoleId(@Param("roleId") Integer roleId);

}
