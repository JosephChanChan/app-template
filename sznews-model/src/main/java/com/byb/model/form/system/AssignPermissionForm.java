package com.byb.model.form.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Joseph
 * @since 2019/7/9 14:06
 */
@Data
@ApiModel(value="分配 Permission 对象", description="接口权限。 ")
public class AssignPermissionForm {

    private Integer roleId;

    @ApiModelProperty(value = "权限id列表",name="permissionIds")
    private List<Integer> permissionIds;

}
