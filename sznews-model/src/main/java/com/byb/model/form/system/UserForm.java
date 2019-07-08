package com.byb.model.form.system;

import com.byb.framework.validate.annotation.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Timestamp;
import java.sql.Date;
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
@ApiModel(value="User对象", description="系统用户表")
public class UserForm {


    private Integer id;

    @NotEmpty
    @ApiModelProperty(value = "用户名",name="userName")
    private String userName;

    @NotEmpty
    @ApiModelProperty(value = "登录名",name="loginName")
    private String loginName;

    @NotEmpty
    @ApiModelProperty(value = "需将明文MD5加密后传递",name="password")
    private String password;

    @ApiModelProperty(value = "加密盐",name="salt")
    private String salt;

    private Integer status;

    private Timestamp createTime;


}
