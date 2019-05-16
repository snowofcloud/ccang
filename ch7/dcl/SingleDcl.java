package com.concurrent.ch7.dcl;

/**
 * @auther xuxq
 * @date 2019/5/16 9:14
 */
public class SingleDcl {
    private static SingleDcl singleDcl;

    private SingleDcl() {
    }

    public static SingleDcl getInstance() {
        if (singleDcl == null) {
            System.out.println(Thread.currentThread() + "is null");
            synchronized (SingleDcl.class) {
                if (singleDcl == null) {
                    System.out.println(Thread.currentThread() + "is null");
                    singleDcl = new SingleDcl();
                }
            }
        }
        return singleDcl;
    }

}
