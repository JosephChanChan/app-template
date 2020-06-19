package com.joseph.template.api.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.joseph.template.model.dto.AppInfoQuery;
import com.joseph.template.model.entity.AppInfo;
import com.joseph.template.model.entity.DataDictionary;
import com.joseph.template.model.vo.PageBean;
import com.joseph.template.service.IAppInfoService;
import com.joseph.template.service.IDataDictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Joseph
 * @since 2020-06-19 22:34
 */
@Slf4j
@Controller
@RequestMapping("/backend")
public class BackendController {

    @Autowired
    private IAppInfoService appInfoService;
    @Autowired
    private IDataDictionaryService dataDictionaryService;


    @GetMapping("mainPage")
    public String mainPage() {
        return "backend/main";
    }

    @GetMapping("appListPage")
    public String appListPage(AppInfoQuery query, HttpServletRequest request) {
        IPage<AppInfo> pages = appInfoService.pageList(query);
        if (pages.getTotal() > 0) {
            List<AppInfo> records = pages.getRecords();
            // 遍历查询其它的信息，懒得写join查询了，就这样吧，虽然性能稍差，但就是个内部系统，谁在意呢？
            records.forEach(appInfo -> {
                DataDictionary platform = dataDictionaryService.query(appInfo.getFlatformId());
                appInfo.setFlatformName(platform.getValueName());
            });
        }
        PageBean<AppInfo> pageBean = new PageBean<>();
        pageBean.init(pages);
        request.setAttribute("pageBean", pageBean);
        return "backend/applist";
    }




}
