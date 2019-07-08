package com.byb.model.form.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Timestamp;
import java.sql.Date;
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
@ApiModel(value="UserRole对象", description="")
public class UserRoleForm {


    private Integer id;

    private Integer userId;

    private Integer roleId;


}
