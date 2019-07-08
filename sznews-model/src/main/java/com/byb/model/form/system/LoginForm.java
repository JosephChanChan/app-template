package com.byb.model.form.system;

import com.byb.framework.validate.annotation.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Joseph
 * @since 2019/7/6 19:05
 */
@Data
@ApiModel(value="User对象", description="系统用户表")
public class LoginForm {

    @NotEmpty
    @ApiModelProperty(value = "登录名",name="login_name")
    private String loginName;

    @NotEmpty
    @ApiModelProperty(value = "需将明文MD5加密后传递",name="password")
    private String password;

}
