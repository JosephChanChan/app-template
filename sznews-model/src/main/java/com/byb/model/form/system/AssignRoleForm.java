package com.byb.model.form.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Joseph
 * @since 2019/7/9 11:13
 */
@Data
@ApiModel(value="分配Role对象", description="")
public class AssignRoleForm {

    private Integer roleId;

    @ApiModelProperty(value = "选中用户id列表",name="selectedUserIds")
    private List<Integer> selectedUserIds;

}
