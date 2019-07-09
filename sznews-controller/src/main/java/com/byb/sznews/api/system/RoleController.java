package com.byb.sznews.api.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.byb.framework.utils.stomp.CommonUtil;
import com.byb.model.entity.system.Role;
import com.byb.model.entity.system.UserRole;
import com.byb.model.form.system.AssignRoleForm;
import com.byb.model.form.system.RoleForm;
import com.byb.model.page.system.RolePageForm;
import com.byb.model.vo.system.RoleVo;
import com.byb.service.system.UserRoleService;
import org.springframework.web.bind.annotation.*;
import com.byb.framework.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.byb.framework.utils.stomp.ReflectionUtils;
import com.byb.framework.annotation.OAuth;
import com.byb.model.vo.system.UserVo;
import com.byb.framework.utils.stomp.DateKit;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


import com.byb.service.system.RoleService;

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

    @PostMapping("insert")
    @ApiOperation(value="添加")
    public Result add(@RequestBody RoleForm form){
        Role role = new Role();
        ReflectionUtils.copyProperties(role,form);
        roleService.save(role);
        return Result.success();
    }

    @PutMapping("edit")
    @ApiOperation(value="编辑")
    public Result edit(@RequestBody RoleForm form){
        if (form.getId() == null) {
            return Result.invalidParam();
        }
        Role role = new Role();
        ReflectionUtils.copyProperties(role,form);
        roleService.updateById(role);
        return Result.success();
    }

    @GetMapping("list")
    @ApiOperation(value="列表")
    public Result list(RolePageForm pageForm){
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (CommonUtil.isNotBlank(pageForm.getKeyword())) {
            queryWrapper.like("role_name", pageForm.getKeyword());
        }
        IPage<Role> page = roleService.page(pageForm, queryWrapper);
        return Result.success().setData(page);
    }

    @DeleteMapping("delete")
    @ApiOperation(value="删除")
    public Result delete(@RequestParam(value="roleId") Integer roleId){
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.eq("role_id", roleId);
        int count = userRoleService.count(userRoleQueryWrapper);
        if (count > 0) {
            return Result.fail(Result.INVALID_PARAM, "该角色下关联了用户，不能删除");
        }
        roleService.removeRole(roleId);
        return Result.success();
    }

    @PutMapping("assignUser")
    @ApiOperation(value="分配用户角色")
    public Result assignUser(@RequestBody AssignRoleForm form){
        if (null == form.getRoleId()) {
            return Result.invalidParam("角色id不能为空");
        }
        roleService.assignUser(form.getRoleId(), form.getSelectedUserIds());
        return Result.success();
    }

    @GetMapping("relationUserList")
    @ApiOperation(value="角色下关联的用户列表")
    public Result relationUserList(RolePageForm pageForm){
        if (pageForm.getRoleId() == null) {
            return Result.invalidParam("角色id不能为空");
        }
        IPage iPage = roleService.relationUserList(pageForm.getRoleId(), pageForm);
        return Result.success().setData(iPage);
    }

    @GetMapping("highLightUserByRole")
    @ApiOperation(value="返回所有用户，但是该角色下的用户会高亮")
    public Result highLightUserByRole(RolePageForm pageForm){
        if (pageForm.getRoleId() == null) {
            return Result.invalidParam("角色id不能为空");
        }
        IPage iPage = roleService.highLightUserByRole(pageForm.getRoleId(), pageForm);
        return Result.success().setData(iPage);
    }





        /*@GetMapping("detail")
    @ApiOperation(value="详情")
    public Result<RoleVo> detail(@RequestParam(value="id") Integer id){
        return Result.<RoleVo>success();
    }*/
}
