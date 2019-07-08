package com.byb.service.impl.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.byb.dao.system.UserMapper;
import com.byb.framework.utils.encoder.PasswordUtils;
import com.byb.model.entity.system.User;
import com.byb.service.impl.BaseServiceImpl;
import com.byb.service.system.UserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectBy(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", userName);
        return getOne(queryWrapper);
    }
}
