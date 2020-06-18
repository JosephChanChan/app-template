package com.joseph.template.api.system;

import com.joseph.template.service.MenuService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;


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
