package com.byb.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.byb.model.entity.system.Role;
import com.byb.model.page.system.RolePageForm;
import com.byb.model.vo.system.UserVo;
import com.byb.service.BaseService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
public interface RoleService extends BaseService<Role> {

    boolean removeRole(Integer roleId);

    void assignUser(Integer roleId, List<Integer> selectedUserIds);

    IPage<UserVo> relationUserList(Integer roleId, RolePageForm pageForm);

    IPage<UserVo> highLightUserByRole(Integer roleId, RolePageForm pageForm);

    List<Role> selectBy(Integer userId);
}
