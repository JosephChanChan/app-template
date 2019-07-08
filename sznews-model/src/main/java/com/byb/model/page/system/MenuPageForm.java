package com.byb.model.page.system;

import java.sql.Timestamp;
import com.byb.model.entity.system.Menu;
import java.util.Date;
import lombok.Data;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
@Data
public class MenuPageForm extends PageForm<Menu>{

    private String keyword;

}
