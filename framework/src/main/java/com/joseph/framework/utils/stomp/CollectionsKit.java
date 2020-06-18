package com.joseph.framework.utils.stomp;

import java.util.*;

/**
 * 集合工具集
 *
 * @author Joseph
 * @since 2019/4/23
 */
public class CollectionsKit {

    /** 集合非空且有元素 */
    public static boolean nonNullAndEmpty (Collection collection) {
        return !nullOrEmpty(collection);
    }
    public static boolean nonNullAndEmpty (Map map) {
        return !nullOrEmpty(map);
    }

    /** 集合为 null 或 无元素 */
    public static boolean nullOrEmpty (Collection collection) {
        if (null == collection) {
            return true;
        }
        return 0 == collection.size();
    }
    public static boolean nullOrEmpty (Map map) {
        if (null == map) {
            return true;
        }
        return 0 == map.size();
    }

    /** 数组为 null */
    public static <T> boolean isNull (T[] array) {
        return null == array;
    }

    /** 空 则返回 不可写入操作 的空集合 */
    public static List insteadOfNull (List list) {
        return null == list ? Collections.EMPTY_LIST : list;
    }
    public static Set insteadOfNull (Set set) {
        return null == set ? Collections.EMPTY_SET : set;
    }
    public static Map insteadOfNull (Map map) {
        return null == map ? Collections.EMPTY_MAP : map;
    }

    /**
     * 手工分页，处理了起始行和结束行异常的容错情况。
     *
     * @param start 起始行
     * @param pageSize 页大小
     * @param originalList 原始列表
     * @return 页内数据
     */
    public static List subList(int start, int pageSize, List originalList) {
        if (null == originalList ||
            0 == originalList.size() ||
            start < 0 || pageSize <= 0) {
            return new ArrayList();
        }
        if (start >= originalList.size()) {
            // 不足一页
            if (pageSize > originalList.size()) {
                return originalList;
            }
            start = originalList.size() - pageSize;// 拿最后一页
        }
        if (start + pageSize > originalList.size()) {
            // 不要多拿，只拿剩余部分
            pageSize = originalList.size() - start;
        }
        return originalList.subList(start, start + pageSize);
    }
}
