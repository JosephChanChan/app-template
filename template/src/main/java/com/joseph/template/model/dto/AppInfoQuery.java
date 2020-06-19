package com.joseph.template.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joseph.template.model.entity.AppInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Joseph
 * @since 2020-06-19 23:02
 */
@Setter
@Getter
public class AppInfoQuery extends Page<AppInfo> {

    private String softwareName;
    private Long flatformId;
    private String categoryLevel1;
    private String categoryLevel2;
    private String categoryLevel3;


}
