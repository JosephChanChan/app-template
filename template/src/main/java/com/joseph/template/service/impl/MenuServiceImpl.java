package com.joseph.template.service.impl;

import com.joseph.template.model.entity.Menu;
import com.joseph.template.dao.MenuMapper;
import com.joseph.template.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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


    @Override
    public List<Menu> selectBy(Integer userId) {
        return menuMapper.selectBy(userId);
    }
}
