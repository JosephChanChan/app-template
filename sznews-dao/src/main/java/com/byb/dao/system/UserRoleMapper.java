package com.byb.dao.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.byb.model.entity.system.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.byb.model.page.system.RolePageForm;
import com.byb.model.vo.system.UserVo;
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
public interface UserRoleMapper extends BaseMapper<UserRole> {


    int insertRoleUser(@Param("roleId") Integer roleId, @Param("userIds") List<Integer> userIds);




}
