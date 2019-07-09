package com.byb.model.form.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

import lombok.Data;

/**
 * <p>
 * 接口权限。

 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
@Data
@ApiModel(value="Permission对象", description="接口权限。 ")
public class PermissionForm {


    private Integer id;

    @ApiModelProperty(value = "权限名",name="permission_name")
    private String permissionName;

    @ApiModelProperty(value = "权限代码",name="permission_code")
    private String permissionCode;

    @ApiModelProperty(value = "资源路径",name="url")
    private String url;


}
