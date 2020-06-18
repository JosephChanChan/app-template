//package com.byb.framework.interceptor;
//
//import com.byb.framework.result.Result;
//import com.byb.framework.utils.stomp.ConstUtils;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 登录过滤拦截器
// */
//public class OAuthInterceptor extends HandlerInterceptorAdapter {
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		if(request.getSession().getAttribute(ConstUtils.SESSION_USER) == null){
//            request.getRequestDispatcher("/error/" + Result.NOT_OAUTH).forward(request, response);
//			return false;
//		}
//		return true;
//	}
//
//}