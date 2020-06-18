package com.joseph.template.dao;

import com.joseph.template.model.entity.UserRole;
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
public interface UserRoleMapper extends BaseMapper<UserRole> {


    int insertRoleUser(@Param("roleId") Integer roleId, @Param("userIds") List<Integer> userIds);




}
