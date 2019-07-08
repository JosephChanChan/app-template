package com.byb.model.page.system;

import com.byb.model.entity.system.Role;
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
public class RolePageForm extends PageForm<Role> {

    private String keyword;

}
