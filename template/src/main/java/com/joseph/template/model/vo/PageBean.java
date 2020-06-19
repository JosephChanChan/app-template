package com.joseph.template.model.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Joseph
 * @since 2020-06-19 23:30
 */
@Setter
@Getter
public class PageBean<T> {

    private List<T> pageIndexList;
    private long totalCount = 0;
    private long pageIndex = 1;
    private long totalPageCount = 0;


    public void init(IPage<T> pages) {
        setPageIndex(pages.getCurrent());
        setPageIndexList(pages.getRecords());
        setTotalCount(pages.getTotal());
        setTotalPageCount(pages.getPages());
    }

}
