package com.joseph.template.service.impl;

import com.joseph.template.dao.BackendUserMapper;
import com.joseph.template.model.entity.BackendUser;
import com.joseph.template.service.IBackendUserService;
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
public class BackendUserServiceImpl extends ServiceImpl<BackendUserMapper, BackendUser> implements IBackendUserService {

}
