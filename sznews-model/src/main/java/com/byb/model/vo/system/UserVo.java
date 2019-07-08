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
 * 系统用户表
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
@Data
@ApiModel(value="User对象", description="系统用户表")
public class UserVo {


    private Integer id;

    @ApiModelProperty(value = "用户名",name="user_name")
    private String userName;

    @ApiModelProperty(value = "登录名",name="login_name")
    private String loginName;

    private String password;

    @ApiModelProperty(value = "加密盐",name="salt")
    private String salt;

    private Integer status;

    private Integer createUserId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;


}
