package com.concurrent.ch7.dcl;

/**
 * @auther xuxq
 * @date 2019/5/16 9:14
 */
public class SingleDcl {
    private volatile static SingleDcl singleDcl;
    private SingleDcl(){}
    public static SingleDcl getInstance(){
        if(singleDcl == null){
            System.out.println(Thread.currentThread() + "SingleDcl为空");
            synchronized (SingleDcl.class) {
                if (singleDcl == null) {
                    System.out.println(Thread.currentThread() + "SingleDcl为空");
                    singleDcl = new SingleDcl();
                }
            }
        }
        return singleDcl;
    }
    /*new一个对象的三个步骤：
        1/申请堆空间
        2/初始化空间
        3/把引用指向堆中对象
        但是，有时候第三步骤会在第二步骤之前执行，所以不加volatile，这个双重检查锁就是有问题的.*/

}
