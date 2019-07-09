package com.byb.sznews.api.system;

import com.byb.model.entity.system.UserRole;
import com.byb.model.form.system.UserRoleForm;
import com.byb.model.page.system.UserRolePageForm;
import com.byb.model.vo.system.UserRoleVo;
import org.springframework.web.bind.annotation.*;
import com.byb.framework.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.byb.framework.utils.stomp.ReflectionUtils;
import com.byb.framework.annotation.OAuth;
import com.byb.model.vo.system.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;


import com.byb.service.system.UserRoleService;

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
@RequestMapping(value="userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    /*@PostMapping("insert")
    @ApiOperation(value="添加")
    public Result add(@RequestBody UserRoleForm form, @OAuth UserVo userVo){
        UserRole userRole = new UserRole();
        ReflectionUtils.copyProperties(userRole,form);
        userRoleService.save(userRole);
        return Result.success();
    }

    @PutMapping("edit")
    @ApiOperation(value="编辑")
    public Result edit(@RequestBody UserRoleForm form,@OAuth UserVo userVo){
        UserRole userRole = new UserRole();
        ReflectionUtils.copyProperties(userRole,form);
        userRoleService.updateById(userRole);
        return Result.success();
    }

    @GetMapping("detail")
    @ApiOperation(value="详情")
    public Result<UserRoleVo> detail(@RequestParam(value="id") Integer id){
        return Result.<UserRoleVo>success();
    }

    @GetMapping("list")
    @ApiOperation(value="列表")
    public Result list(UserRolePageForm pageForm){
        return Result.success();
    }

    @DeleteMapping("delete")
    @ApiOperation(value="删除")
    public Result delete(@RequestParam(value="id") Integer id){
        return Result.success();
    }*/

}
