package com.byb.sznews.system;

import com.byb.model.entity.system.Menu;
import com.byb.model.form.system.MenuForm;
import com.byb.model.page.system.MenuPageForm;
import com.byb.model.vo.system.MenuVo;
import org.springframework.web.bind.annotation.*;
import com.byb.framework.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.byb.framework.utils.stomp.ReflectionUtils;
import com.byb.framework.annotation.OAuth;
import com.byb.model.vo.system.UserVo;
import com.byb.framework.utils.stomp.DateKit;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Timestamp;
import java.util.Date;


import com.byb.service.system.MenuService;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 * @Author: Joseph
 * Date:     2019-07-06
 * Description:
 */
@Controller
@Api(description="菜单表API")
@RequestMapping(value="menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("insert")
    @ApiOperation(value="添加")
    public Result add(@RequestBody MenuForm form, @OAuth UserVo userVo){
        Menu menu = new Menu();
        ReflectionUtils.copyProperties(menu,form);
        menuService.save(menu);
        return Result.success();
    }

    @PutMapping("edit")
    @ApiOperation(value="编辑")
    public Result edit(@RequestBody MenuForm form,@OAuth UserVo userVo){
        Menu menu = new Menu();
        ReflectionUtils.copyProperties(menu,form);
        menuService.updateById(menu);
        return Result.success();
    }

    @GetMapping("detail")
    @ApiOperation(value="详情")
    public Result<MenuVo> detail(@RequestParam(value="id") Integer id){
        return Result.<MenuVo>success();
    }

    @GetMapping("list")
    @ApiOperation(value="列表")
    public Result list(MenuPageForm pageForm){
        return Result.success();
    }

    @DeleteMapping("delete")
    @ApiOperation(value="删除")
    public Result delete(@RequestParam(value="id") Integer id){
        return Result.success();
    }

}
