package com.c503.hthj.asoco.dangerchemical.waste.util;

/**
 * @auther xuxq
 * @date 2019/4/11 11:13
 */
public class UseJoin {

    //需求：如何保证线程A在线程B执行完之后执行，使用join,
    static class JumpQuence implements Runnable {
        //插入的线程
        private Thread thread;
        public JumpQuence (Thread thread) {
            this.thread = thread;
        }

        public void run() {
            try {
                System.out.println(thread.getName() + "will be join before" + Thread.currentThread().getName());
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "terminted.");
        }
    }

    public static void main(String[] args) {
        Thread previous = Thread.currentThread();//现在是主线程
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new JumpQuence(previous), String.valueOf(i));
            System.out.println(previous.getName() + "jump a quence the thread:" + thread.getName());
            thread.start();
            previous = thread;
        }
        SleepTools.second(2);//让主线程休眠2秒
        System.out.println(Thread.currentThread().getName() + "terminate.");
    }

}
