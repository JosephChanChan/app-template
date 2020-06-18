//package com.byb.framework.interceptor;
//
//import com.byb.framework.annotation.OAuth;
//import com.byb.framework.result.Result;
//import com.byb.framework.utils.stomp.ConstUtils;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Set;
//
///**
// * 资源权限控制拦截
// */
//public class SecurityInterceptor extends HandlerInterceptorAdapter {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        HandlerMethod method = (HandlerMethod) handler;
//        OAuth oAuth = method.getMethodAnnotation(OAuth.class);
//        if(oAuth == null || !oAuth.required()){
//            return true;
//        }
//        Set<String> resources = (Set<String>)request.getSession().getAttribute(ConstUtils.SESSION_USER_ALLOW_RIGHT);
//        if (resources != null && resources.size() > 0) {
//            if (resources.contains(oAuth.code())) {
//                return true;
//            }
//        }
//        request.getRequestDispatcher("/error/" + Result.PERMISSION_DENIED).forward(request, response);
//        return false;
//    }
//}
