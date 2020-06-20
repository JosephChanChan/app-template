package com.joseph.template.service.impl;

import com.joseph.template.dao.AppCategoryMapper;
import com.joseph.template.model.entity.AppCategory;
import com.joseph.template.service.IAppCategoryService;
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
public class AppCategoryServiceImpl extends ServiceImpl<AppCategoryMapper, AppCategory> implements IAppCategoryService {

    @Autowired
    private AppCategoryMapper appCategoryMapper;


    @Override
    public AppCategory query(long id) {
        return appCategoryMapper.selectById(id);
    }





}
