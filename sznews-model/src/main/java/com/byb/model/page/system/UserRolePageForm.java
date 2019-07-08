package com.byb.model.page.system;

import java.sql.Timestamp;
import com.byb.model.entity.system.UserRole;
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
public class UserRolePageForm extends PageForm<UserRole>{

    private String keyword;

}
