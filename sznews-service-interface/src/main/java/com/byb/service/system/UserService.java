package com.byb.service.system;

import com.byb.model.entity.system.User;
import com.byb.service.BaseService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
public interface UserService extends BaseService<User> {

    User selectBy(String loginName);

    boolean modifyUserPsd(Integer userId, String newPsd);

}
