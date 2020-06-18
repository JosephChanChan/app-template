package com.joseph.template.filter;

import com.joseph.framework.constant.ShiroUrl;
import com.joseph.template.model.entity.User;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Joseph
 * @since 2019/7/6 14:20
 */
public class PermissionFilter extends AccessControlFilter {

    private Logger logger = LoggerFactory.getLogger(PermissionFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);

        logger.info("isAccessAllowed begin...");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String uri = httpServletRequest.getRequestURI();//获取URI
        String basePath = httpServletRequest.getContextPath();//获取basePath
        if (null != uri && uri.startsWith(basePath)) {
            uri = uri.replaceFirst(basePath, "");
        }
        if (uri.equals("/")) {// 根目录/直接视为有权限
            return Boolean.TRUE;
        }
        if (subject.isPermitted(uri)) {
            return Boolean.TRUE;
        }

        /*if (ShiroFilterUtils.isAjax(request)) {
            Map<String, String> resultMap = new HashMap<String, String>();
            LoggerUtils.debug(getClass(), "isAccessAllowed, 当前用户没有登录，并且是Ajax请求！");
            resultMap.put("login_status", "300");
            resultMap.put("message", "\u5F53\u524D\u7528\u6237\u6CA1\u6709\u767B\u5F55\uFF01");//当前用户没有登录！
            ShiroFilterUtils.out(response, resultMap);
        }*/

        User principal = (User) subject.getPrincipal();
        String loginName = principal.getLoginName();
        logger.info("isAccessAllowed, 用户" + loginName + "没权限访问" + uri);

        return Boolean.FALSE;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        logger.info("onAccessDenied begin...");

        Subject subject = getSubject(request, response);
        if (null == subject.getPrincipal()) {// 表示没有登录，重定向到登录页面
            saveRequest(request);
            WebUtils.issueRedirect(request, response, ShiroUrl.loginUrl);
        } else {
            if (StringUtils.hasText(ShiroUrl.unauthorizedUrl)) {// 如果有未授权页面跳转过去
                WebUtils.issueRedirect(request, response, ShiroUrl.unauthorizedUrl);
            } else {//否则返回401未授权状态码
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        return Boolean.FALSE;
    }
}
