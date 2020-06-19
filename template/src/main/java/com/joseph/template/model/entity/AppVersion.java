package com.joseph.template.model.entity;

import java.math.BigDecimal;
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
public class AppVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * appId（来源于：app_info表的主键id）
     */
    @TableField("appId")
    private Long appId;

    /**
     * 版本号
     */
    @TableField("versionNo")
    private String versionNo;

    /**
     * 版本介绍
     */
    @TableField("versionInfo")
    private String versionInfo;

    /**
     * 发布状态（来源于：data_dictionary，1 不发布 2 已发布 3 预发布）
     */
    @TableField("publishStatus")
    private Long publishStatus;

    /**
     * 下载链接
     */
    @TableField("downloadLink")
    private String downloadLink;

    /**
     * 版本大小（单位：M）
     */
    @TableField("versionSize")
    private BigDecimal versionSize;

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
     * apk文件的服务器存储路径
     */
    @TableField("apkLocPath")
    private String apkLocPath;

    /**
     * 上传的apk文件名称
     */
    @TableField("apkFileName")
    private String apkFileName;


}
