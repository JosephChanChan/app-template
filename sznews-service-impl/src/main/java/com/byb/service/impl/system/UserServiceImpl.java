package com.byb.service.impl.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.byb.dao.system.UserMapper;
import com.byb.framework.exception.ParameterException;
import com.byb.framework.utils.encoder.PasswordUtils;
import com.byb.model.entity.system.User;
import com.byb.service.impl.BaseServiceImpl;
import com.byb.service.system.UserService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectBy(String loginName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", loginName);
        return getOne(queryWrapper);
    }


    /**
     * 管理员修改用户密码，包括自己的
     *
     * @param userId userId
     * @param newPsd newPsd 必须已经 md5() 加密过的密码，否则可能登陆不上
     * @return
     */
    @Override
    public boolean modifyUserPsd(Integer userId, String newPsd) {
        User user = getById(userId);
        if (null == user) {
            throw new ParameterException("用户id=" + userId + "不存在！");
        }
        String salt = user.getSalt();
        String encrypt = PasswordUtils.encrypt(newPsd, salt);
        int count = userMapper.modifyUserPsd(userId, encrypt);

        User admin = (User) SecurityUtils.getSubject().getPrincipal();
        logger.info("modifyUserPsd() admin {} has modified the password for user id {}. result count {}",
                admin.getLoginName(), userId, count);
        return count == 1;
    }
}
