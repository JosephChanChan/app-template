package com.joseph.framework.exception;

/**
 * @author chzeting
 */
public enum ErrorResponseMsgEnum {

	//用户错误，以1开头
	USER_DOMAIN_AUTH_FAIL("100001","身份验证失败", "身份验证失败"),
	USER_DOMAIN_CONNECTION_FAIL("100002","AD域连接失败", "AD域连接失败"),
	USER_DOMAIN_UNKNOW_FAIL("100003","AD域连接失败", "AD域连接失败"),
	USER_FILE_NOT_NULL("100004","文件不能为空", "文件不能为空"),
	USER_EXISTS_REGISTER("100005","用户已注册", "用户已注册"),
	USER_ROLE_ALLOT("100006","未分配角色，请联系管理员", "未分配角色，请联系管理员"),
	USER_STATUS_ENABLED("100007","用户已被禁用", "用户已被禁用"),
	USER_LOGIN_ERROR("100007","账号或密码不正确", "账号或密码不正确"),
	USER_DIFF_OP("100008","非管理员不允许操作其他项目资料", "非管理员不允许操作其他项目资料"),

	//Tag 异常
	TAG_EXISTS ("200001", "已有存在的标签", "已有存在的标签"),
	RESOURCE_NOT_FOUND ("-1004","资源不存在",null),

	INVALID_PARAM("40001", "参数不合法", "请按照接口文档检查参数"),


	CONNECTION_TIMEOUT("400011", "请求地址调用超时","请求地址调用超时"),
	;

	private final String code;
	private final String message;
	private final String solution;

	ErrorResponseMsgEnum(String code, String message, String solution) {
		this.code = code;
		this.message = message;
		this.solution = solution;
	}

	public static String messageByCode(String code) {
		ErrorResponseMsgEnum fromValue = fromValue(code);
		if (fromValue == null) {
			return "未知类型";
		}
		return fromValue.getMessage();
	}

	public static ErrorResponseMsgEnum fromValue(String code) {
		for (ErrorResponseMsgEnum each : ErrorResponseMsgEnum.values()) {
			if (each.getCode().equalsIgnoreCase(code)) {
				return each;
			}
		}
		return null;
	}

	public String formatSolution(Object... args){
		return String.format(this.solution, args);
	}

	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public String getSolution() {
		return solution;
	}
}