package com.joseph.template.service;

import com.joseph.template.model.entity.Menu;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
public interface MenuService extends BaseService<Menu> {


    List<Menu> selectBy(Integer userId);

}
