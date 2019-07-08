package ${package.Controller};

import org.springframework.web.bind.annotation.*;
import com.byb.framework.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.byb.framework.utils.stomp.ReflectionUtils;
import com.byb.framework.annotation.OAuth;
import com.byb.model.vo.system.UserVo;
import com.byb.framework.utils.stomp.DateKit;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import java.sql.Timestamp;
import java.util.Date;


import ${package.Service}.${table.serviceName};

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 * @Author: ${author}
 * Date:     ${date}
 * Description:
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
@Api(description="${table.comment!}API")
@RequestMapping(value="${table.entityName?substring(0,1)?lower_case}${table.entityName?substring(1)}")
public class ${table.controllerName} {

    @Autowired
    private ${table.serviceName} ${table.serviceName?substring(0,1)?lower_case}${table.serviceName?substring(1)};
</#if>

    @PostMapping("insert")
    @ApiOperation(value="添加")
    public Result add(@RequestBody ${entity}Form form,@OAuth UserVo userVo){
        ${entity} ${entity?substring(0,1)?lower_case}${entity?substring(1)} = new ${entity}();
        ReflectionUtils.copyProperties(${entity?substring(0,1)?lower_case}${entity?substring(1)},form);
        ${entity?substring(0,1)?lower_case}${entity?substring(1)}.setCreateUserId(userVo.getId());
        ${entity?substring(0,1)?lower_case}${entity?substring(1)}.setCreateTime(new Timestamp(DateKit.now().getTime()));
        ${table.serviceName?substring(0,1)?lower_case}${table.serviceName?substring(1)}.save(${entity?substring(0,1)?lower_case}${entity?substring(1)});
        return Result.success();
    }

    @PutMapping("edit")
    @ApiOperation(value="编辑")
    public Result edit(@RequestBody ${entity}Form form,@OAuth UserVo userVo){
        ${entity} ${entity?substring(0,1)?lower_case}${entity?substring(1)} = new ${entity}();
        ReflectionUtils.copyProperties(${entity?substring(0,1)?lower_case}${entity?substring(1)},form);
        ${entity?substring(0,1)?lower_case}${entity?substring(1)}.setUpdateTime(new Timestamp(new Date().getTime()));
        ${entity?substring(0,1)?lower_case}${entity?substring(1)}.setUpdateUserId(userVo.getId());
        ${table.serviceName?substring(0,1)?lower_case}${table.serviceName?substring(1)}.updateById(${entity?substring(0,1)?lower_case}${entity?substring(1)});
        return Result.success();
    }

    @GetMapping("detail")
    @ApiOperation(value="详情")
    public Result<${entity}Vo> detail(@RequestParam(value="id") Integer id){
        return Result.<${entity}Vo>success();
    }

    @GetMapping("list")
    @ApiOperation(value="列表")
    public Result list(${entity}PageForm pageForm){
        return Result.success();
    }

    @DeleteMapping("delete")
    @ApiOperation(value="删除")
    public Result delete(@RequestParam(value="id") Integer id){
        return Result.success();
    }

}
</#if>
