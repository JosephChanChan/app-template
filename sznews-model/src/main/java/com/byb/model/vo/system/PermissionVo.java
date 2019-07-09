package com.byb.model.vo.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Timestamp;
import java.sql.Date;
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
public class PermissionVo {


    private Integer id;

    @ApiModelProperty(value = "权限名",name="permission_name")
    private String permissionName;

    @ApiModelProperty(value = "权限代码",name="permission_code")
    private String permissionCode;

    @ApiModelProperty(value = "资源路径",name="url")
    private String url;

    @ApiModelProperty(value = "选中权限 0未选中，1选中",name="checkedPermission")
    private Integer checkedPermission;


}
