package com.joseph.template.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Joseph Chan
 * @since 2020-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DevUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 开发者帐号
     */
    @TableField("devCode")
    private String devCode;

    /**
     * 开发者名称
     */
    @TableField("devName")
    private String devName;

    /**
     * 开发者密码
     */
    @TableField("devPassword")
    private String devPassword;

    /**
     * 开发者电子邮箱
     */
    @TableField("devEmail")
    private String devEmail;

    /**
     * 开发者简介
     */
    @TableField("devInfo")
    private String devInfo;

    /**
     * 创建者（来源于backend_user用户表的用户id）
     */
    @TableField("createdBy")
    private Long createdBy;

    /**
     * 创建时间
     */
    @TableField("creationDate")
    private LocalDateTime creationDate;

    /**
     * 更新者（来源于backend_user用户表的用户id）
     */
    @TableField("modifyBy")
    private Long modifyBy;

    /**
     * 最新更新时间
     */
    @TableField("modifyDate")
    private LocalDateTime modifyDate;


}
