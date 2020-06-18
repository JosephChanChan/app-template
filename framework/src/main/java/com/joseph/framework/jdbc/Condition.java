package com.joseph.framework.jdbc;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author chenzeting
 * 查询条件
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Condition {

    /**
     * 查询条件对应的属性字段
     * @return
     */
    String property() default "";

    /**
     * 条件比较方式
     * @return
     */
    Operator operator() default Operator.EQ;
}
