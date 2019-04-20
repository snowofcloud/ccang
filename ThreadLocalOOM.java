package com.concurrent.ch1.thread.local;

import java.util.Timer;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @auther xuxq
 * @date 2019/4/20 9:56
 */
public class ThreadLocalOOM {

    private static final int TASK_LOOP_SIZE = 500;

    final static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,5,
            1, TimeUnit.MINUTES,new LinkedBlockingDeque<>());

    static class LocalVariable {
        private byte[] a = new byte[1024*1024*5];
    }

    final static ThreadLocal<LocalVariable> localVariable = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        for (int i =0; i<TASK_LOOP_SIZE; i++) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    //new LocalVariable();//第一次启动
                    localVariable.set(new LocalVariable());//第二次启动
                    System.out.println("use local variable!!!");
                    localVariable.remove();//第三次启动
                }
            });
            Thread.sleep(100);
        }
        System.out.println("pool execute over!");

    }




}
