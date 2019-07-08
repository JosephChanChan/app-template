package com.byb.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OAuth {

    /**
     * 是否需要授权
     * @return
     */
    boolean required() default true;

    /**
     * 权限code
     * @return
     */
    String code() default "";

}
