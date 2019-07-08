package com.byb.sznews.system;

import com.byb.framework.utils.stomp.DateKit;
import com.byb.model.entity.system.Permission;
import com.byb.model.form.system.PermissionForm;
import com.byb.model.page.system.PermissionPageForm;
import com.byb.model.vo.system.PermissionVo;
import org.springframework.web.bind.annotation.*;
import com.byb.framework.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.byb.framework.utils.stomp.ReflectionUtils;
import com.byb.framework.annotation.OAuth;
import com.byb.model.vo.system.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Timestamp;
import java.util.Date;


import com.byb.service.system.PermissionService;

/**
 * <p>
 * 接口权限。
 前端控制器
 * </p>
 * @Author: Joseph
 * Date:     2019-07-06
 * Description:
 */
@Controller
@Api(description="接口权限。 API")
@RequestMapping(value="permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("insert")
    @ApiOperation(value="添加")
    public Result add(@RequestBody PermissionForm form, @OAuth UserVo userVo){
        Permission permission = new Permission();
        ReflectionUtils.copyProperties(permission,form);
        permissionService.save(permission);
        return Result.success();
    }

    @PutMapping("edit")
    @ApiOperation(value="编辑")
    public Result edit(@RequestBody PermissionForm form,@OAuth UserVo userVo){
        Permission permission = new Permission();
        ReflectionUtils.copyProperties(permission,form);
        permissionService.updateById(permission);
        return Result.success();
    }

    @GetMapping("detail")
    @ApiOperation(value="详情")
    public Result<PermissionVo> detail(@RequestParam(value="id") Integer id){
        return Result.<PermissionVo>success();
    }

    @GetMapping("list")
    @ApiOperation(value="列表")
    public Result list(PermissionPageForm pageForm){
        return Result.success();
    }

    @DeleteMapping("delete")
    @ApiOperation(value="删除")
    public Result delete(@RequestParam(value="id") Integer id){
        return Result.success();
    }

}
