package com.joseph.template.api.system;

import com.joseph.template.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.joseph.framework.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 * @Author: Joseph
 * Date:     2019-07-06
 * Description:
 */
@Controller
@Api(description="系统用户表API")
@RequestMapping(value="user")
public class UserController {

    @Autowired
    private UserService userService;



    @PutMapping("modifyUserPsd")
    @ApiOperation(value="修改用户的密码")
    public Result modifyUserPsd(Integer userId, String newPsd){
        boolean success = userService.modifyUserPsd(userId, newPsd);
        return success ? Result.success() : Result.notFound();
    }

    /*@PostMapping("insert")
    @ApiOperation(value="添加")
    public Result add(@RequestBody UserForm form, @OAuth UserVo userVo){
        User user = new User();
        ReflectionUtils.copyProperties(user,form);
        user.setCreateTime(new Timestamp(DateKit.now().getTime()));
        userService.save(user);
        return Result.success();
    }

    @PutMapping("edit")
    @ApiOperation(value="编辑")
    public Result edit(@RequestBody UserForm form,@OAuth UserVo userVo){
        User user = new User();
        ReflectionUtils.copyProperties(user,form);
        userService.updateById(user);
        return Result.success();
    }

    @GetMapping("detail")
    @ApiOperation(value="详情")
    public Result<UserVo> detail(@RequestParam(value="id") Integer id){
        return Result.<UserVo>success();
    }

    @GetMapping("list")
    @ApiOperation(value="列表")
    public Result list(UserPageForm pageForm){
        return Result.success();
    }

    @DeleteMapping("delete")
    @ApiOperation(value="删除")
    public Result delete(@RequestParam(value="id") Integer id){
        return Result.success();
    }*/

}
