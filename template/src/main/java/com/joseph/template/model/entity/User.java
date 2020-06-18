package com.joseph.template.model.entity;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

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

    private Timestamp createTime;


}
