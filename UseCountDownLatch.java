package com.concurrent.ch2.tools;

import com.concurrent.tools.SleepTools;

import java.util.ServiceLoader;
import java.util.concurrent.CountDownLatch;

/**
 * @auther xuxq
 * @date 2019/4/23 0:08
 */
public class UseCountDownLatch {

    static CountDownLatch countDownLatch = new CountDownLatch(6);

    //初始化线程
    private static class InitThread implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread_" + Thread.currentThread().getId() + " ready init work...");
            for (int i = 0; i <2; i++) {
                System.out.println("Thread_" + Thread.currentThread().getId() + " ...continue do its work");
            }
        }
    }

    //业务线程等待latch的计数器为0完成
    private static class BusiThread implements Runnable {
        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0;i<3; i++) {
                System.out.println("BusiThread_" + Thread.currentThread().getId() + " do business......");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SleepTools.ms(1);
                System.out.println("Thread_" + Thread.currentThread().getId()+" ready init work step 1st...");
                countDownLatch.countDown();
                System.out.println("begin step 2nd...");
                SleepTools.ms(1);
                System.out.println("Thread_" + Thread.currentThread().getId()+" ready init work step 2nd...");
                countDownLatch.countDown();
            }
        }).start();
        new Thread(new BusiThread()).start();
        for (int i = 0;  i<=3; i++) {
            Thread thread = new Thread(new InitThread());
            thread.start();
        }
        countDownLatch.await();
        System.out.println("Main do ites work......................");
    }

}
