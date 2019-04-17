package com.concurrent.ch1.base;

import java.util.concurrent.ExecutionException;

/**
 * @auther xuxq
 * @date 2019/4/18 0:15
 * 守护线程的使用
 */
public class DaemonThread {

    private static class UseThread extends Thread {
        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " i am extends thread");
                }
                System.out.println(Thread.currentThread().getName() + " interrupt flag is" + isInterrupted());
            } finally {
                //守护线程不一定会执行,
                System.out.println("//////finally");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        UseThread useThread = new UseThread();
        useThread.setDaemon(true);
        useThread.start();
        Thread.sleep(3);
        useThread.interrupt();

    }

}
