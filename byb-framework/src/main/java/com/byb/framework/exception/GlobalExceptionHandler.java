package com.byb.framework.exception;

import com.byb.framework.annotation.OAuth;
import com.byb.framework.result.Result;
import com.byb.framework.utils.stomp.ConstUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 */
@ControllerAdvice
@RequestMapping("/error")
@Slf4j
public class GlobalExceptionHandler extends BasicErrorController {

	private ErrorAttributes errorAttributes;

	@Autowired
	public GlobalExceptionHandler(ErrorAttributes errorAttributes) {
		super(errorAttributes, new ErrorProperties());
		this.errorAttributes = errorAttributes;
	}

	@OAuth(required = false)
	@RequestMapping(Result.NOT_OAUTH)
	public Result unauthorized(){
		return Result.unauthorized();
	}

	@OAuth(required = false)
	@RequestMapping(Result.PERMISSION_DENIED)
	public Result permissionDenied(){
		return Result.permissionDenied();
	}

	@OAuth(required = false)
	@RequestMapping(Result.SUPER_PERMISSION_DENIED)
	public Result superPermissionDenied(){
		return Result.superPermissionDenied();
	}

	@OAuth(required = false)
	@RequestMapping(Result.INVALID_PARAM)
	public Result invalidParam(ModelMap modelMap){
		return new Result(Result.INVALID_PARAM, modelMap.get("message").toString());
	}

	@OAuth(required = false)
	@RequestMapping(Result.SYSTEM_BUSY)
	public Result systemBusy(HttpServletRequest request) {
		String returnMessage = (String) request.getAttribute(ConstUtils.RESULT_RETURN_MESSAGE);
		Result result = Result.systemBusy();
		if(StringUtils.isEmpty(returnMessage)){
			result.setMessage(returnMessage);
		}
		return result;
	}

	/**
	 * 系统全局异常处理
	 * @param request
	 * @return
	 */
	@OAuth(required = false)
	@GetMapping(value = {"/500", ""})
	public Result err(HttpServletRequest request) {
		HttpStatus status = getStatus(request);
		Result result = Result.systemError();
		if(HttpStatus.NOT_FOUND.equals(status)) {
			result = Result.notFound();
		}
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		Throwable throwable = this.errorAttributes.getError(requestAttributes);
		if(throwable == null){
			return result;
		}
		if(throwable instanceof ApplicationException){
			ApplicationException ex = (ApplicationException) throwable;
			result.setCode(ex.getCode());
			result.setMessage(ex.getMessage());
			log.info("{} at {}", ex.getMessage(), ex.getCause().getStackTrace()[0].toString());
		}else {
			log.error("未知异常：" + ExceptionUtils.getFullStackTrace(throwable));
		}
		return result;
	}

	/**
	 * Service层全局异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public Result handleException(Exception e) {
		if(e instanceof ApplicationException){
			log.info("{} at {}", e.getMessage(), e.getStackTrace()[0].toString());
			ApplicationException ex = (ApplicationException) e;
			return new Result(ex.getCode(), ex.getMessage());
		}
		if (e instanceof ParameterException) {
			log.info("{} at {}", e.getMessage(), e.getStackTrace()[0].toString());
			ParameterException ex = (ParameterException) e;
			return new Result(Result.DEFICIENCY_PARAM, ex.getMessage());
		}
		if(e instanceof HttpRequestMethodNotSupportedException){
			return new Result(Result.INVALID_REQUEST_METHOD, e.getMessage());
		}
		log.error(ExceptionUtils.getFullStackTrace(e));
		return Result.systemError();
	}

}