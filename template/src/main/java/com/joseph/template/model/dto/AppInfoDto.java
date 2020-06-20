package com.joseph.template.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Joseph
 * @since 2020-06-20 19:03
 */
@Setter
@Getter
public class AppInfoDto {



    /**
     * 主键id
     */
    private Long id;

    /**
     * 软件名称
     */
    private String softwareName;

    /**
     * APK名称（唯一）
     */
    private String APKName;

    /**
     * 支持ROM
     */
    private String supportROM;

    /**
     * 界面语言
     */
    private String interfaceLanguage;

    /**
     * 软件大小（单位：M）
     */
    private BigDecimal softwareSize;

    /**
     * 开发者id（来源于：dev_user表的开发者id）
     */
    private Long devId;

    /**
     * 应用简介
     */
    private String appInfo;

    /**
     * 状态（来源于：data_dictionary，1 待审核 2 审核通过 3 审核不通过 4 已上架 5 已下架）
     */
    private Long status;

    private String statusName;

    /**
     * 上架时间
     */
    private LocalDateTime onSaleDate;

    /**
     * 下架时间
     */
    private LocalDateTime offSaleDate;

    /**
     * 所属平台（来源于：data_dictionary，1 手机 2 平板 3 通用）
     */
    private Long flatformId;

    /**
     * 所属三级分类（来源于：data_dictionary）
     */
    private Long categoryLevel3;

    /**
     * 下载量（单位：次）
     */
    private Long downloads;

    /**
     * 所属一级分类（来源于：data_dictionary）
     */
    private Long categoryLevel1;

    /**
     * 所属二级分类（来源于：data_dictionary）
     */
    private Long categoryLevel2;

    /**
     * LOGO图片url路径
     */
    private String logoPicPath;

    /**
     * LOGO图片的服务器存储路径
     */
    private String logoLocPath;

    /**
     * 最新的版本id
     */
    private Long versionId;


    private MultipartFile logoPicture;

}
