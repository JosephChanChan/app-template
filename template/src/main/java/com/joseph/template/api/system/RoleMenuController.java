package com.joseph.template.api.system;

import com.joseph.template.service.RoleMenuService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 *  前端控制器
 * </p>
 * @Author: Joseph
 * Date:     2019-07-06
 * Description:
 */
@Controller
@Api(description="API")
@RequestMapping(value="roleMenu")
public class RoleMenuController {

    @Autowired
    private RoleMenuService roleMenuService;

    /*@PostMapping("insert")
    @ApiOperation(value="添加")
    public Result add(@RequestBody RoleMenuForm form, @OAuth UserVo userVo){
        RoleMenu roleMenu = new RoleMenu();
        ReflectionUtils.copyProperties(roleMenu,form);
        roleMenuService.save(roleMenu);
        return Result.success();
    }

    @PutMapping("edit")
    @ApiOperation(value="编辑")
    public Result edit(@RequestBody RoleMenuForm form,@OAuth UserVo userVo){
        RoleMenu roleMenu = new RoleMenu();
        ReflectionUtils.copyProperties(roleMenu,form);
        roleMenuService.updateById(roleMenu);
        return Result.success();
    }

    @GetMapping("detail")
    @ApiOperation(value="详情")
    public Result<RoleMenuVo> detail(@RequestParam(value="id") Integer id){
        return Result.<RoleMenuVo>success();
    }

    @GetMapping("list")
    @ApiOperation(value="列表")
    public Result list(RoleMenuPageForm pageForm){
        return Result.success();
    }

    @DeleteMapping("delete")
    @ApiOperation(value="删除")
    public Result delete(@RequestParam(value="id") Integer id){
        return Result.success();
    }*/

}
