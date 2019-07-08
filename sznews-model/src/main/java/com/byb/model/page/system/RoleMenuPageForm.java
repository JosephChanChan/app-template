package com.byb.model.page.system;

import java.sql.Timestamp;
import com.byb.model.entity.system.RoleMenu;
import java.util.Date;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
@Data
public class RoleMenuPageForm extends PageForm<RoleMenu>{

    private String keyword;

}
