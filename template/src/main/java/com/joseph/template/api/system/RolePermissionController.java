package com.joseph.template.api.system;

import com.joseph.template.service.RolePermissionService;
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
@RequestMapping(value="rolePermission")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    /*@PostMapping("insert")
    @ApiOperation(value="添加")
    public Result add(@RequestBody RolePermissionForm form, @OAuth UserVo userVo){
        RolePermission rolePermission = new RolePermission();
        ReflectionUtils.copyProperties(rolePermission,form);
        rolePermissionService.save(rolePermission);
        return Result.success();
    }

    @PutMapping("edit")
    @ApiOperation(value="编辑")
    public Result edit(@RequestBody RolePermissionForm form,@OAuth UserVo userVo){
        RolePermission rolePermission = new RolePermission();
        ReflectionUtils.copyProperties(rolePermission,form);
        rolePermissionService.updateById(rolePermission);
        return Result.success();
    }

    @GetMapping("detail")
    @ApiOperation(value="详情")
    public Result<RolePermissionVo> detail(@RequestParam(value="id") Integer id){
        return Result.<RolePermissionVo>success();
    }

    @GetMapping("list")
    @ApiOperation(value="列表")
    public Result list(RolePermissionPageForm pageForm){
        return Result.success();
    }

    @DeleteMapping("delete")
    @ApiOperation(value="删除")
    public Result delete(@RequestParam(value="id") Integer id){
        return Result.success();
    }*/

}
