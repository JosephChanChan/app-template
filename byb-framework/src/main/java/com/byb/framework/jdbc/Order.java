package com.byb.framework.jdbc;


import org.springframework.data.domain.Sort;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chenzeting
 * 分页排序
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Order {

    /**
     * 排序字段
     * @return
     */
    String property() default "";

    /**
     * 排序方向
     * @return
     */
    Sort.Direction direction() default Sort.Direction.DESC;
}
