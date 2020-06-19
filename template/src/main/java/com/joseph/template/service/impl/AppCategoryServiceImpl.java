package com.joseph.template.service.impl;

import com.joseph.template.dao.AppCategoryMapper;
import com.joseph.template.model.entity.AppCategory;
import com.joseph.template.service.IAppCategoryService;
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
public class AppCategoryServiceImpl extends ServiceImpl<AppCategoryMapper, AppCategory> implements IAppCategoryService {

}
