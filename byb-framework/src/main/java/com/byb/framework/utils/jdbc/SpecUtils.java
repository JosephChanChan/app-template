package com.byb.framework.utils.jdbc;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.byb.framework.jdbc.Condition;
import com.byb.framework.jdbc.Order;
import com.byb.framework.utils.stomp.ReflectionUtils;
import com.byb.framework.utils.stomp.StringUtils;
import org.springframework.data.domain.Range;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * @Author: chenzeting
 * Date:     2019/2/12
 * Description:
 */
public class SpecUtils {

    /**
     * 构建复合查询条件
     * @param page
     * @param <T>
     * @return
     */
    public static <T> Wrapper<T> condition(Page<T> page) {
        Field[] fields = page.getClass().getDeclaredFields();
        QueryWrapper<T> qw = null;
        Condition condition;
        String property;
        Field upperField = null;
        Field lowerField = null;
        for (Field field : fields) {
            condition = field.getAnnotation(Condition.class);
            if(condition == null){
                continue;
            }
            Object value = ReflectionUtils.getFieldValue(page, field.getName());
            if(value == null){
                continue;
            }
            if (qw == null) {
                qw = new QueryWrapper<>();
            }
            String name = StringUtils.isEmpty(condition.property()) ? field.getName() : condition.property();
            property = com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(name);
            switch (condition.operator()){
                case EQ:
                    qw.eq(property, value);
                    break;
                case GT:
                    qw.gt(property, value);
                    break;
                case GE:
                    qw.ge(property, value);
                    break;
                case LE:
                    qw.le(property, value);
                    break;
                case LT:
                    qw.lt(property, value);
                    break;
                case IN:
                    if(value instanceof Collection){
                        qw.in(property, (Collection) value);
                    }else{
                        qw.in(property, value);
                    }
                    break;
                case NOT_IN:
                    if(value instanceof Collection){
                        qw.notIn(property, (Collection) value);
                    }else{
                        qw.notIn(property, value);
                    }
                    break;
                case LIKE:
                    qw.like(property, value);
                    break;
                case LIKE_LEFT:
                    qw.likeLeft(property, value);
                    break;
                case LIKE_RIGHT:
                    qw.likeRight(property, value);
                    break;
                case NE:
                    qw.ne(property,value);
                    break;
                case BETWEEN_LOWER:
                    lowerField = field;
                    break;
                case BETWEEN_UPPER:
                    upperField = field;
                    break;
            }
        }
        if(upperField != null || lowerField != null){
            String name;
            Object upper = null;
            Object lower = null;
            if(upperField != null){
                condition = upperField.getAnnotation(Condition.class);
                name = StringUtils.isEmpty(condition.property()) ? upperField.getName() : condition.property();
                property = com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(name);
                upper = ReflectionUtils.getFieldValue(page, upperField.getName());
            } else {
                condition = lowerField.getAnnotation(Condition.class);
                name = StringUtils.isEmpty(condition.property()) ? lowerField.getName() : condition.property();
                property = com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(name);
                lower = ReflectionUtils.getFieldValue(page, lowerField.getName());
            }
            if (upper != null && lower != null) {
                qw.between(property, lower, upper);
            } else if (upper != null) {
                qw.lt(property, upper);
            } else if (lower != null) {
                qw.gt(property, lower);
            }
        }
        return qw;
    }

    /**
     * 构建分页查询条件
     * @param page
     * @param <T>
     */
    public static <T> void pageable(Page<T> page) {
        Field[] fields = page.getClass().getDeclaredFields();
        Order order;
        for (Field field : fields) {
            order = field.getAnnotation(Order.class);
            if (order == null) {
                continue;
            }
            String property = StringUtils.isEmpty(order.property()) ? field.getName() : order.property();
            property = com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(property);
            Sort.Direction direction = order.direction();
            if (direction.isAscending()) {
                page.setAsc(property);
            } else {
                page.setDesc(property);
            }
        }
    }
}
