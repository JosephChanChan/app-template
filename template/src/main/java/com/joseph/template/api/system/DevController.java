package com.joseph.template.api.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.joseph.framework.result.Result;
import com.joseph.framework.utils.stomp.CollectionsKit;
import com.joseph.framework.utils.stomp.CommonUtil;
import com.joseph.framework.utils.stomp.DateKit;
import com.joseph.template.component.FileComponent;
import com.joseph.template.model.dto.AppInfoDto;
import com.joseph.template.model.dto.AppInfoQuery;
import com.joseph.template.model.entity.AppCategory;
import com.joseph.template.model.entity.AppInfo;
import com.joseph.template.model.entity.DataDictionary;
import com.joseph.template.model.entity.DevUser;
import com.joseph.template.model.vo.PageBean;
import com.joseph.template.service.IAppCategoryService;
import com.joseph.template.service.IAppInfoService;
import com.joseph.template.service.IDataDictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Joseph
 * @since 2020-06-19 22:34
 */
@Slf4j
@Controller
@RequestMapping("/dev")
public class DevController {

    @Autowired
    private IAppInfoService appInfoService;
    @Autowired
    private IDataDictionaryService dataDictionaryService;
    @Autowired
    private IAppCategoryService appCategoryService;
    @Autowired
    private FileComponent fileComponent;


    @GetMapping("devIndexPage")
    public String mainPage() {
        return "developer/main";
    }

    @GetMapping("appListPage")
    public String appListPage(AppInfoQuery query, HttpServletRequest request) {
        IPage<AppInfo> pages = appInfoService.pageList(query);
        if (pages.getTotal() > 0) {
            List<AppInfo> records = pages.getRecords();
            // data_dictionary 关联了几个字段，无法通过Join一次取得全部数据，会有重复行
            records.forEach(appInfo -> {
                // flatForm
                DataDictionary platform = dataDictionaryService.query(appInfo.getFlatformId());
                appInfo.setFlatformName(platform.getValueName());
                // status
                // 状态直接枚举类，为什么还要加表里？又不会变，多一次IO很好吗
                QueryWrapper<DataDictionary> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("typeCode", "APP_STATUS").eq("valueId", appInfo.getStatus());
                DataDictionary status = dataDictionaryService.getOne(queryWrapper);
                appInfo.setStatusName(status.getValueName());
                // category name
                List<AppCategory> list = new ArrayList<>(3);
                getCategory(appInfo.getCategoryLevel3(), list);
                // category3 category2 category1
                appInfo.setCategory1(list.get(2));
                appInfo.setCategory2(list.get(1));
                appInfo.setCategory3(list.get(0));
            });
        }

        // status
        QueryWrapper<DataDictionary> q1 = new QueryWrapper<>();
        q1.eq("typeCode", "APP_STATUS");
        List<DataDictionary> statusList = dataDictionaryService.list(q1);
        request.setAttribute("statusList", statusList);

        // flatForm
        QueryWrapper<DataDictionary> q3 = new QueryWrapper<>();
        q3.eq("typeCode", "APP_FLATFORM");
        List<DataDictionary> flatList = dataDictionaryService.list(q3);
        request.setAttribute("flatFormList", flatList);

        // root category
        Map<String, List<AppCategory>> map = new TreeMap<>();

        QueryWrapper<AppCategory> q2 = new QueryWrapper<>();
        q2.isNull("parentId");
        List<AppCategory> root = appCategoryService.list(q2);
        map.put("categoryLevelList1", root);
        calcCategoryGrouping(root, map, 2);
        request.setAttribute("map", map);

        PageBean<AppInfo> pageBean = new PageBean<>();
        pageBean.init(pages);
        request.setAttribute("pageBean", pageBean);
        // 让页面回显参数对吧？
        request.setAttribute("param", query);
        return "developer/appinfolist";
    }


    @GetMapping("categoryList")
    @ResponseBody
    public Result<List<AppCategory>> categoryList(@RequestParam String id) {
        QueryWrapper<AppCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentId", id);
        List<AppCategory> list = appCategoryService.list(queryWrapper);
        return Result.<List<AppCategory>>success().setData(list);
    }

    @GetMapping("addAppPage")
    public String appPage(HttpServletRequest request) {
        // flatFormList
        QueryWrapper<DataDictionary> q3 = new QueryWrapper<>();
        q3.eq("typeCode", "APP_FLATFORM");
        List<DataDictionary> flatList = dataDictionaryService.list(q3);
        request.setAttribute("flatFormList", flatList);

        // root category
        QueryWrapper<AppCategory> q2 = new QueryWrapper<>();
        q2.isNull("parentId");
        List<AppCategory> root = appCategoryService.list(q2);
        request.setAttribute("categoryLevelList1", root);

        return "developer/appinfoadd";
    }

    @PostMapping("addApp")
    public Result addApp(AppInfoDto appInfoDto, HttpServletRequest request) {
        DevUser devUser = (DevUser) request.getSession().getAttribute("devUserSession");

        // save img
        String img ;
        try {
            img = fileComponent.uploadPicture(appInfoDto.getLogoPicture());
        }
        catch (IOException e) {
            log.error("save file error!", e);
            return Result.fail(Result.SYSTEM_ERROR, Result.SYSTEM_ERROR);
        }

        AppInfo appInfo = new AppInfo();
        BeanUtils.copyProperties(appInfoDto, appInfo);
        appInfo.setCreationDate(DateKit.now());
        appInfo.setCreatedBy(null == devUser ? 1 : devUser.getId());
        appInfo.setLogoPicPath(img);
        appInfoService.save(appInfo);

        return Result.success();
    }



    /*---------------------------------------------- internal method -------------------------------------------------*/

    /* 递归检索分类 */
    private void getCategory(Long category, List<AppCategory> list) {
        if (null == category) return ;

        AppCategory cate = appCategoryService.query(category);
        list.add(cate);
        getCategory(cate.getParentId(), list);
    }

    /**
     * 递归计算所有分层的category，有维护level的话就不用如此计算，太麻烦
     */
    private void calcCategoryGrouping(List<AppCategory> list, Map<String, List<AppCategory>> map, int level) {
        if (CollectionsKit.nonNullAndEmpty(list)) {
            QueryWrapper<AppCategory> queryWrapper = new QueryWrapper<>();
            List<Long> idList = list.stream().map(AppCategory::getId).collect(Collectors.toList());
            queryWrapper.in("parentId", idList);
            List<AppCategory> childList= appCategoryService.list(queryWrapper);
            map.put("categoryLevelList" + level, childList);
            calcCategoryGrouping(childList, map, level + 1);
        }
    }


}
