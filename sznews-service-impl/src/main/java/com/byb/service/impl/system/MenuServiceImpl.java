package com.byb.service.impl.system;

import com.byb.model.entity.system.Menu;
import com.byb.dao.system.MenuMapper;
import com.byb.service.impl.BaseServiceImpl;
import com.byb.service.system.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
}
