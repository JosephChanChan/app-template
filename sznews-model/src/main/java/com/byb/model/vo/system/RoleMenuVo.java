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
 * 
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
@Data
@ApiModel(value="RoleMenu对象", description="")
public class RoleMenuVo {


    private Integer id;

    private Integer roleId;

    private Integer menuId;


}
