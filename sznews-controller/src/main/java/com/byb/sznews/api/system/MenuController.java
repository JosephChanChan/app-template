package com.byb.sznews.api.system;

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
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;


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

    /*@GetMapping("detail")
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
    }*/

}
