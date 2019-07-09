package com.byb.model.form.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.byb.framework.validate.annotation.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

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

    @TableId(type = IdType.AUTO)
    private Integer id;

    @NotEmpty
    @ApiModelProperty(value = "角色名",name="role_name")
    private String roleName;

    @NotEmpty
    @ApiModelProperty(value = "角色代码",name="role_code")
    private String roleCode;


}
