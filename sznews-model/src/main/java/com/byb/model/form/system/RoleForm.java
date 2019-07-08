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
@ApiModel(value="Role对象", description="")
public class RoleForm {


    private Integer id;

    @ApiModelProperty(value = "角色名",name="role_name")
    private String roleName;

    @ApiModelProperty(value = "角色代码",name="role_code")
    private String roleCode;


}
