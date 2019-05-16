package com.concurrent.ch7.dcl;

/**
 * @auther xuxq
 * @date 2019/5/16 9:04
 */
public class InstanceLazy {

    private Integer value;
    private static Integer val;

    public InstanceLazy(Integer value) {
        super();
        this.value = value;
    }

    public static class InstanceHolder{
        public static Integer val = new Integer(100);
    }

    public Integer getValue() {
        return value;
    }

    public static Integer getVal() {
        return InstanceHolder.val;
    }
}
