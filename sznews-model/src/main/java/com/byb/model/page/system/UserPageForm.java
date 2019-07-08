package com.byb.model.page.system;

import com.byb.model.entity.system.User;
import lombok.Data;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
@Data
public class UserPageForm extends PageForm<User> {

    private String keyword;

}
