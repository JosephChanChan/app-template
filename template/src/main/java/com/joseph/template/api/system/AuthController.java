package com.joseph.template.api.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joseph.framework.result.Result;
import com.joseph.framework.utils.encoder.PasswordUtils;
import com.joseph.template.model.dto.LoginDto;
import com.joseph.template.model.dto.UserDto;
import com.joseph.template.model.entity.BackendUser;
import com.joseph.template.model.entity.DataDictionary;
import com.joseph.template.model.entity.DevUser;
import com.joseph.template.model.entity.User;
import com.joseph.template.service.IBackendUserService;
import com.joseph.template.service.IDataDictionaryService;
import com.joseph.template.service.IDevUserService;
import com.joseph.template.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Joseph
 * @since 2019/7/6 16:53
 */
@Api("认证API")
@Controller
@RequestMapping(value="auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private IBackendUserService backendUserService;
    @Autowired
    private IDevUserService devUserService;
    @Autowired
    private IDataDictionaryService dictionaryService;


    @PostMapping("/register")
    @ApiOperation(value="注册")
    @ResponseBody
    public Result register(UserDto userDto) {
        return Result.success();
    }

    @PostMapping("/login")
    @ApiOperation(value="登录")
    @ResponseBody
    public Result login(LoginDto loginDto, HttpServletRequest request) {
        if ("backend".equals(loginDto.getRealm().toLowerCase())) {
            QueryWrapper<BackendUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userCode", loginDto.getLoginName())
                        .eq("userPassword", loginDto.getCipher());
            BackendUser one = backendUserService.getOne(queryWrapper);
            if (null == one) {
                return Result.success().setData(false);
            }
            DataDictionary data = dictionaryService.query(one.getUserType());
            one.setDataDictionary(data);
            request.getSession().setAttribute("userSession", one);
        }
        else {
            QueryWrapper<DevUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("devName", loginDto.getLoginName())
                        .eq("devPassword", loginDto.getCipher());
            DevUser one = devUserService.getOne(queryWrapper);
            if (null == one) {
                return Result.success().setData(false);
            }
            request.getSession().setAttribute("devUserSession", one);
        }
        return Result.success().setData(true);
    }






}
