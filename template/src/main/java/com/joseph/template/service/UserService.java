package com.joseph.template.service;

import com.joseph.template.model.entity.User;

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
