package com.byb.sznews.api.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.byb.framework.result.Result;
import com.byb.framework.utils.encoder.PasswordUtils;
import com.byb.model.entity.system.Menu;
import com.byb.model.entity.system.User;
import com.byb.model.form.system.LoginForm;
import com.byb.model.form.system.UserForm;
import com.byb.service.system.MenuService;
import com.byb.service.system.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Joseph
 * @since 2019/7/6 16:53
 */
@Controller
@Api(description="认证API")
@RequestMapping(value="auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;


    @PostMapping("/register")
    @ApiOperation(value="注册")
    public Result register(UserForm userForm) {
        // 登录名唯一限制
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", userForm.getLoginName());
        int count = userService.count(queryWrapper);
        // 虽然这样做有很小的一点时间间隔，但是没关系，用户量很小
        if (count > 0) {
            return Result.fail(Result.INVALID_PARAM, "登录名已存在，请更改！");
        }
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        // Md5 ( 密码+盐 )
        user.setSalt(userForm.getLoginName());
        String encrypt = PasswordUtils.encrypt(user.getPassword(), user.getSalt());
        user.setPassword(encrypt);
        boolean save = userService.save(user);
        return save ? Result.success() : Result.systemError();
    }

    @PostMapping("/login")
    @ApiOperation(value="登录")
    public Result login(LoginForm loginForm) {
        UsernamePasswordToken token = new UsernamePasswordToken(loginForm.getLoginName(), loginForm.getPassword());
        try {
            SecurityUtils.getSubject().login(token);
        }
        catch (DisabledAccountException e) {
            logger.error("帐号已经禁用！", e);
            return Result.fail(Result.ACCOUNT_LOCKED, "帐号已经禁用。");
        }
        catch (AccountException e) {
            logger.error("帐号或密码错误！", e);
            return Result.fail(Result.INVALID_PARAM, "帐号或密码错误");
        }
        catch (Exception e) {
            logger.error(" login error !", e);
            return Result.systemError();
        }

        // --- 登录成功，返回角色菜单
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        Integer userId = principal.getId();
        List<Menu> menus = menuService.selectBy(userId);
        return Result.success().setData(menus);
    }






}
