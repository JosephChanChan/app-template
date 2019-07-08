package com.byb.model.form.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Timestamp;
import java.sql.Date;
import lombok.Data;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
@Data
@ApiModel(value="Menu对象", description="菜单表")
public class MenuForm {


    private Integer id;

    @ApiModelProperty(value = "菜单名",name="menu_name")
    private String menuName;

    @ApiModelProperty(value = "菜单码",name="menu_code")
    private String menuCode;

    private Integer parentId;


}
