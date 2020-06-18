package com.joseph.template.model.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Joseph
 * @since 2020-06-03 22:05
 */
@Data
public class UserDto {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登录名, 登录名应该唯一，建唯一索引
     */
    private String loginName;

    private String password;

    /**
     * 加密盐
     */
    private String salt;

    private Integer status;

}
