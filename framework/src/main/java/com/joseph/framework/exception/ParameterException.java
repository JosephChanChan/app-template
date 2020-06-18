package com.joseph.framework.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Joseph
 * @date_time: 2019/6/13 14:37
 */
@Setter
@Getter
public class ParameterException extends RuntimeException {

    private String message;

    public ParameterException(String message) {
        super(message);
        this.message = message;
    }


}
