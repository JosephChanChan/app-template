package com.byb.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.byb.model.entity.system.Role;
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
