package com.joseph.template.service.impl;

import com.joseph.template.dao.AppVersionMapper;
import com.joseph.template.model.entity.AppVersion;
import com.joseph.template.service.IAppVersionService;
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
public class AppVersionServiceImpl extends ServiceImpl<AppVersionMapper, AppVersion> implements IAppVersionService {

}
