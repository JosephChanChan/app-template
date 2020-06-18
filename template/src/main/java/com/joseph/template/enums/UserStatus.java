package com.joseph.template.enums;

/**
 * @author Joseph
 * @since 2019/7/8 15:24
 */
public enum UserStatus {


    NORMAL(1, "正常状态"),
    ABNORMAL(2, "异常状态"),
    FREEZE(3, "冻结状态");

    int code;
    String status;

    UserStatus(Integer code, String status) {
        this.code = code;
        this.status = status;
    }
}
