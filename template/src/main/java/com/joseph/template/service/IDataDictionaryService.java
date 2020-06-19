package com.joseph.template.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joseph.template.model.entity.DataDictionary;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Joseph Chan
 * @since 2020-06-19
 */
public interface IDataDictionaryService extends IService<DataDictionary> {


    DataDictionary query(long valueId);
}
