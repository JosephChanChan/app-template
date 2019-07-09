package com.byb.sznews.api.system;

import com.byb.model.entity.system.RoleMenu;
import com.byb.model.form.system.RoleMenuForm;
import com.byb.model.page.system.RoleMenuPageForm;
import com.byb.model.vo.system.RoleMenuVo;
import org.springframework.web.bind.annotation.*;
import com.byb.framework.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.byb.framework.utils.stomp.ReflectionUtils;
import com.byb.framework.annotation.OAuth;
import com.byb.model.vo.system.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;


import com.byb.service.system.RoleMenuService;

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
