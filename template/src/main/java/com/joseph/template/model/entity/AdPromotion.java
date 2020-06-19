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
public class AdPromotion implements Serializable {

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
     * 广告图片存储路径
     */
    @TableField("adPicPath")
    private String adPicPath;

    /**
     * 广告点击量
     */
    @TableField("adPV")
    private Long adPV;

    /**
     * 轮播位（1-n）
     */
    @TableField("carouselPosition")
    private Integer carouselPosition;

    /**
     * 起效时间
     */
    @TableField("startTime")
    private LocalDateTime startTime;

    /**
     * 失效时间
     */
    @TableField("endTime")
    private LocalDateTime endTime;

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
