package com.joseph.template.service.impl;

import com.joseph.template.dao.DevUserMapper;
import com.joseph.template.model.entity.DevUser;
import com.joseph.template.service.IDevUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Joseph Chan
 * @since 2020-06-19
 */
@Service
public class DevUserServiceImpl
        extends ServiceImpl<DevUserMapper, DevUser> implements IDevUserService {

}
