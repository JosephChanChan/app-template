package com.joseph.framework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 针对字符串日期格式做校验，不用于 java.sql.Date 和 java.util.Date
 *
 * @author Joseph
 * @since 2019/6/19 16:39
 */
@Getter
@AllArgsConstructor
public enum DateTypeEnum {

    // 这个正则不能正确的匹配所有情况，如: 2019-19 会返回 true，只是做个大概的格式校验 确保是 YYYY-MM
    YYYY_MM("YYYY-MM", "2\\d{3}-[01]\\d{1}"),
    // 这个正则不能正确的匹配所有情况，如: 2019-19-33 会返回 true，只是做个大概的格式校验 确保是 YYYY-MM-dd
    YYYY_MM_DD("YYYY-MM-dd", "2\\d{3}-[01]\\d{1}-[0123]\\d{1}");


    private String pattern;
    private String regex;


    public static DateTypeEnum getEnum(String pattern) {
        DateTypeEnum[] values = DateTypeEnum.values();
        if (values.length > 0) {
            for (DateTypeEnum dateTypeEnum : values) {
                if (dateTypeEnum.getPattern().equals(pattern)) {
                    return dateTypeEnum;
                }
            }
        }
        return null;
    }

    /*public static void main(String[] args) {
        String value = "2019-19";
        Pattern pattern = Pattern.compile(YYYY_MM.regex);
        System.out.println(pattern.matcher(value).matches());

        DateTypeEnum anEnum = getEnum("YYYY-MM-dd");
        System.out.println(anEnum.getRegex());
    }*/
}
