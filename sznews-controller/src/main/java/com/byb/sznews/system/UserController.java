package com.byb.sznews.system;

import com.byb.framework.utils.stomp.DateKit;
import com.byb.model.entity.system.User;
import com.byb.model.form.system.UserForm;
import com.byb.model.page.system.UserPageForm;
import com.byb.model.vo.system.UserVo;
import com.byb.service.system.UserService;
import org.springframework.web.bind.annotation.*;
import com.byb.framework.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.byb.framework.utils.stomp.ReflectionUtils;
import com.byb.framework.annotation.OAuth;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Timestamp;
import java.util.Date;


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

    @PostMapping("insert")
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
    }

}
