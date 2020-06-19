package com.joseph.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joseph.template.dao.DataDictionaryMapper;
import com.joseph.template.model.entity.DataDictionary;
import com.joseph.template.service.IDataDictionaryService;
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
public class DataDictionaryServiceImpl
        extends ServiceImpl<DataDictionaryMapper, DataDictionary> implements IDataDictionaryService {


    @Override
    public DataDictionary query(long valueId) {
        QueryWrapper<DataDictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("valueId", valueId);
        return getOne(queryWrapper);
    }

}
