package com.byb.sznews.aspect;

import com.byb.framework.enums.DateTypeEnum;
import com.byb.framework.exception.ApplicationException;
import com.byb.framework.exception.ParameterException;
import com.byb.framework.validate.annotation.DateType;
import com.byb.framework.validate.annotation.NotEmpty;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Joseph
 * @date_time: 2019/6/13 14:04
 */
@Aspect
@Component
public class ParameterCheckAspect {

    private static Logger logger = LoggerFactory.getLogger(ParameterCheckAspect.class);

    private static Map<String, Pattern> dateTypePattern = new HashMap<>();

    static {
        dateTypePattern.put(DateTypeEnum.YYYY_MM.getPattern(), Pattern.compile(DateTypeEnum.YYYY_MM.getRegex()));
        dateTypePattern.put(DateTypeEnum.YYYY_MM_DD.getPattern(), Pattern.compile(DateTypeEnum.YYYY_MM_DD.getRegex()));
    }


    @Pointcut("execution(public * com.byb.sznews.api.*.*.*(..))")
    public void controllerAdvice() {
        // 对 Controller 层的切点，借此切点做一些需要的动态操作
    }

    // 例如这里校验 controller 层的每个public方法的实参
    @Before("controllerAdvice()")
    public void doCheckParameterValidity(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (null == arg) continue;
            String argName = arg.getClass().getSimpleName();
            Field[] fields = arg.getClass().getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                Class<?> fieldType = field.getType();
                Object fieldValue ;

                try {
                    fieldValue = getFieldValue(field, arg);
                }
                catch (IllegalAccessException ex) {
                    logger.error("Check field {} of arg {} of method {} in class {} error!", fieldName, argName, methodName, className, ex);
                    continue;
                }

                checkNull(field, fieldName, fieldType, fieldValue);
                checkDateFormat(field, fieldName, fieldType, fieldValue);
            }
        }
    }

    private void checkNull(Field field, String fieldName, Class<?> fieldType, Object fieldValue) {
        NotEmpty notEmptyAnn = field.getAnnotation(NotEmpty.class);
        if (null != notEmptyAnn) {
            if (null == fieldValue) {
                throw new ParameterException("Parameter " + fieldName +" mustn't be null !");
            }
            if (notEmptyAnn.isCollection() &&
                ((Collection) fieldValue).size() == 0) {
                throw new ParameterException("Parameter " + fieldName +" mustn't be empty !");
            }
            if (fieldType.equals(String.class) && StringUtils.isBlank((String) fieldValue)) {
                throw new ParameterException("Parameter " + fieldName +" mustn't be blank !");
            }
        }
    }

    private void checkDateFormat(Field field, String fieldName, Class<?> fieldType, Object fieldValue) {
        DateType dateTypeAnn = field.getAnnotation(DateType.class);
        if (null != dateTypeAnn && null != fieldValue) {
            String patternAnn = dateTypeAnn.pattern().getPattern();
            Pattern pattern = dateTypePattern.get(patternAnn);
            if (null == pattern) {
                throw new ApplicationException("-1", "Can't get Pattern which is correspond to "+ patternAnn);
            }
            if (!fieldType.equals(String.class)) {
                throw new ApplicationException("-1", "The Annotation DateType is used on String filed !");
            }
            if (!pattern.matcher((String) fieldValue).matches()) {
                throw new ParameterException("Parameter " + fieldName +" must is correct format to "+ patternAnn +" !");
            }
        }
    }

    private Object getFieldValue(Field field, Object arg) throws IllegalAccessException {
        if (!Modifier.isPublic(field.getModifiers())) {
            field.setAccessible(true);
        }
        return field.get(arg);
    }
}