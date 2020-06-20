package com.joseph.template.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.joseph.template.model.dto.AppInfoQuery;
import com.joseph.template.model.entity.AppInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Joseph Chan
 * @since 2020-06-19
 */
public interface AppInfoMapper extends BaseMapper<AppInfo> {


    /*IPage<AppInfo> pageList(@Param("page") AppInfoQuery query);*/

}
