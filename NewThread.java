package com.concurrent.ch1.base;

/**
 * @auther xuxq
 * @date 2019/4/17 15:48
 * 新启一个线程的两种方式：
 */
public class NewThread {

    /*继承Thread类，新启一个线程*/
    private static class UseThread extends Thread {
        @Override
        public void run() {
            System.out.println("the UseThread extends Thread");
        }
    }


    private static class UserRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("the UserRunnable implements Runnable");
        }
    }

    public static void main(String[] args) {
        UseThread useThread = new UseThread();
        useThread.start();
        UserRunnable userRunnable = new UserRunnable();
        new Thread(userRunnable).start();
    }

}
