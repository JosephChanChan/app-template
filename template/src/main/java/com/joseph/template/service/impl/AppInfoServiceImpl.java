package com.joseph.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.joseph.framework.utils.stomp.StringUtils;
import com.joseph.template.dao.AppInfoMapper;
import com.joseph.template.model.dto.AppInfoQuery;
import com.joseph.template.model.entity.AppInfo;
import com.joseph.template.service.IAppInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Joseph Chan
 * @since 2020-06-19
 */
@Slf4j
@Service
public class AppInfoServiceImpl
        extends ServiceImpl<AppInfoMapper, AppInfo> implements IAppInfoService {

    @Autowired
    private AppInfoMapper appInfoMapper;


    @Override
    public IPage<AppInfo> pageList(AppInfoQuery query) {
        QueryWrapper<AppInfo> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(query.getSoftwareName())) {
            queryWrapper.eq("softwareName", query.getSoftwareName());
        }
        if (null != query.getFlatformId()) {
            queryWrapper.eq("flatformId", query.getFlatformId());
        }
        if (null != query.getStatus()) {
            queryWrapper.eq("status", query.getStatus());
        }
        if (!StringUtils.isEmpty(query.getCategoryLevel1())) {
            queryWrapper.eq("categoryLevel1", query.getCategoryLevel1());
        }
        if (!StringUtils.isEmpty(query.getCategoryLevel2())) {
            queryWrapper.eq("categoryLevel2", query.getCategoryLevel2());
        }
        if (!StringUtils.isEmpty(query.getCategoryLevel3())) {
            queryWrapper.eq("categoryLevel3", query.getCategoryLevel3());
        }
        return appInfoMapper.selectPage(query, queryWrapper);
    }









}
