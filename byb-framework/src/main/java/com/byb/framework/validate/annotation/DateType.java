package com.byb.framework.validate.annotation;

import com.byb.framework.enums.DateTypeEnum;

import java.lang.annotation.*;

/**
 * 验证日期格式
 *
 * @author Joseph
 * @date_time: 2019/6/19 16:35
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateType {

    DateTypeEnum pattern() default DateTypeEnum.YYYY_MM;

}
