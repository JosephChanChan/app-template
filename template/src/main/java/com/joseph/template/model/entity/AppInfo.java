package com.joseph.template.model.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
public class AppInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 软件名称
     */
    @TableField("softwareName")
    private String softwareName;

    /**
     * APK名称（唯一）
     */
    @TableField("APKName")
    private String APKName;

    /**
     * 支持ROM
     */
    @TableField("supportROM")
    private String supportROM;

    /**
     * 界面语言
     */
    @TableField("interfaceLanguage")
    private String interfaceLanguage;

    /**
     * 软件大小（单位：M）
     */
    @TableField("softwareSize")
    private BigDecimal softwareSize;

    /**
     * 更新日期
     */
    @TableField("updateDate")
    private LocalDate updateDate;

    /**
     * 开发者id（来源于：dev_user表的开发者id）
     */
    @TableField("devId")
    private Long devId;

    /**
     * 应用简介
     */
    @TableField("appInfo")
    private String appInfo;

    /**
     * 状态（来源于：data_dictionary，1 待审核 2 审核通过 3 审核不通过 4 已上架 5 已下架）
     */
    private Long status;

    /**
     * 上架时间
     */
    @TableField("onSaleDate")
    private LocalDateTime onSaleDate;

    /**
     * 下架时间
     */
    @TableField("offSaleDate")
    private LocalDateTime offSaleDate;

    /**
     * 所属平台（来源于：data_dictionary，1 手机 2 平板 3 通用）
     */
    @TableField("flatformId")
    private Long flatformId;

    /**
     * 所属三级分类（来源于：data_dictionary）
     */
    @TableField("categoryLevel3")
    private Long categoryLevel3;

    /**
     * 下载量（单位：次）
     */
    private Long downloads;

    /**
     * 创建者（来源于dev_user开发者信息表的用户id）
     */
    @TableField("createdBy")
    private Long createdBy;

    /**
     * 创建时间
     */
    @TableField("creationDate")
    private LocalDateTime creationDate;

    /**
     * 更新者（来源于dev_user开发者信息表的用户id）
     */
    @TableField("modifyBy")
    private Long modifyBy;

    /**
     * 最新更新时间
     */
    @TableField("modifyDate")
    private LocalDateTime modifyDate;

    /**
     * 所属一级分类（来源于：data_dictionary）
     */
    @TableField("categoryLevel1")
    private Long categoryLevel1;

    /**
     * 所属二级分类（来源于：data_dictionary）
     */
    @TableField("categoryLevel2")
    private Long categoryLevel2;

    /**
     * LOGO图片url路径
     */
    @TableField("logoPicPath")
    private String logoPicPath;

    /**
     * LOGO图片的服务器存储路径
     */
    @TableField("logoLocPath")
    private String logoLocPath;

    /**
     * 最新的版本id
     */
    @TableField("versionId")
    private Long versionId;


    @TableField(exist = false)
    private String flatformName;


}
