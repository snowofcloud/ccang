package com.c503.hthj.asoco.dangerchemical.waste.util;

/**
 * @auther xuxq
 * @date 2019/4/11 13:00
 */
public class SleepLock {
    private Object lock = new Object();

    public static void main(String[] args) {
        SleepLock sleepTest = new SleepLock();
        Thread threadA = sleepTest.new ThreadSleep();
        threadA.setName("ThreadSleep");
        Thread threadB = sleepTest.new ThreadNotSleep();
        threadB.setName("ThreadNotSleep");
        threadA.start();

        try {
            Thread.sleep(1000);
            System.out.println(" Main slept!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB.start();

    }

    private class ThreadSleep extends Thread{
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "will take the lock");
            try {
                synchronized (lock) {
                    System.out.println(threadName + "taking the lock");

                    System.out.println("Finish the work" + threadName);
                }
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class ThreadNotSleep extends Thread {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + "will take the lock time=" + System.currentTimeMillis());
            synchronized (lock) {
                System.out.println(name + "taking the lock time=" + System.currentTimeMillis());
                System.out.println("Finish the work: " + name);
            }
        }
    }




}
