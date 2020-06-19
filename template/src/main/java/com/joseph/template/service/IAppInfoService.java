package com.joseph.template.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.joseph.template.model.dto.AppInfoQuery;
import com.joseph.template.model.entity.AppInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Joseph Chan
 * @since 2020-06-19
 */
public interface IAppInfoService extends IService<AppInfo> {


    IPage<AppInfo> pageList(AppInfoQuery appInfoQuery);


}
