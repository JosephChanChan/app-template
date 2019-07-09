package com.byb.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.byb.model.entity.system.User;
import com.byb.model.page.system.RolePageForm;
import com.byb.model.vo.system.UserVo;
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


    IPage<UserVo> relationUserList(RolePageForm pageForm, @Param("roleId") Integer roleId);

    IPage<UserVo> highLightUserByRole(RolePageForm pageForm, @Param("roleId") Integer roleId);

    int modifyUserPsd(@Param("userId") Integer userId, @Param("password") String newPsd);
}
