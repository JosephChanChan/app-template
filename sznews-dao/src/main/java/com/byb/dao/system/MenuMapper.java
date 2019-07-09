package com.byb.dao.system;

import com.byb.model.entity.system.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> selectBy(Integer userId);
}
