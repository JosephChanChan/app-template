package com.byb.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.byb.model.entity.system.User;
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

}
