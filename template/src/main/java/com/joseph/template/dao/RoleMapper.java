package com.joseph.template.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.joseph.template.model.entity.Role;
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
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectByUserId(Integer userId);
}
