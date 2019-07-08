package com.byb.framework.constant;

/**
 * @Author: hhj
 * Date: 2019/5/28
 * Description:
 */
public class BusinessStatus {

    /**
     * 新增|待分配
     */
    public final static int added=0;

    /**
     * 驳回
     */
    public final static int overrule=1;

    /**
     * 作废
     */
    public final static int invalid=9;


    /**
     * 审核中
     */
    public final static int no_audit=2;

    /**
     * 审核通过
     */
    public final static int pass=3;

    /**
     * 已签约
     */
    public final static int sign=11;

    /**
     * 已签约收款
     */
    public final static int signing_pay=12;

}
