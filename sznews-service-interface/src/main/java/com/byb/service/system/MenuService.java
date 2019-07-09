package com.byb.service.system;

import com.byb.model.entity.system.Menu;
import com.byb.service.BaseService;

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
