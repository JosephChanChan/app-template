package com.joseph.template.model.dto;

import lombok.Data;

/**
 * @author Joseph
 * @since 2020-06-03 22:06
 */
@Data
public class LoginDto {

    private String loginName;
    private String cipher;

    private String realm = "dev";
}
