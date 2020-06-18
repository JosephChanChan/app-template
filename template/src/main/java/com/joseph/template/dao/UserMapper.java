package com.joseph.template.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.joseph.template.model.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
@Repository
public interface UserMapper extends BaseMapper<User> {


    int modifyUserPsd(@Param("userId") Integer userId, @Param("password") String newPsd);
}
