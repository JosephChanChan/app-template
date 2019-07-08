package com.byb.framework.constant;

/**
 * @Author: hhj
 * Date: 2019/5/22
 * Description:
 */
public class CarRentDepositStatus {

    /**
     *新增/待分配
     */
    public static final int added=0;

    /**
     *待审批/审批中
     */
    public static final int no_audit=1;

    /**
     *通过
     */
    public static final int pass=2;

    /**
     *驳回
     */
    public static final int no_pass=3;

    /**
     *已作废
     */
    public static final int invalid=4;

}
