package com.joseph.template.api.system;

import com.joseph.template.service.RoleService;
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
 * Date:     2019-07-08
 * Description:
 */
@Controller
@Api(description="角色管理 API")
@RequestMapping(value="role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

}
