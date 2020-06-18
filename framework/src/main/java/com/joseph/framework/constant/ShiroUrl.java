package com.joseph.framework.constant;

import com.joseph.framework.result.Result;

/**
 * @author Joseph
 * @since 2019/7/9 11:31
 */
public class ShiroUrl {

    public static final String loginUrl = "/error/" + Result.NOT_OAUTH;
    public static final String successUrl = "/";
    public static final String unauthorizedUrl = "/error/" + Result.PERMISSION_DENIED;

}
