package com.byb.model.entity.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 接口权限。

 * </p>
 *
 * @author Joseph
 * @since 2019-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("permission")
public class Permission implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 权限名
    */
    private String permissionName;

    /**
    * 权限代码
    */
    private String permissionCode;

    /**
    * 资源路径
    */
    private String url;


}
