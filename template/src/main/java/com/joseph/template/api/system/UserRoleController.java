package com.joseph.template.api.system;

import com.joseph.template.service.UserRoleService;
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
