package com.concurrent.ch1.syn;

import com.concurrent.tools.SleepTools;

/**
 * @auther xuxq
 * @date 2019/4/18 18:29
 */
public class StaticAndClass {

    private static class SynClass extends Thread {
        @Override
        public void run() {
            System.out.println(currentThread().getName() + " :synClass is running...");
            synClass();
        }
    }

    private static class SynStatic extends Thread {
        @Override
        public void run() {
            System.out.println(currentThread().getName() + " :synStatic is running...");
            synStatic();
        }
    }

    //类锁
    private static synchronized void synClass() {
        System.out.println(Thread.currentThread().getName() + " synClass is going...");
        SleepTools.second(1);
        System.out.println(Thread.currentThread().getName() + " synClass end");
    }

    //锁static变量
    private static Object obj = new Object();
    private static void synStatic () {
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + " synStatic is going...");
            SleepTools.second(1);
            System.out.println(Thread.currentThread().getName() + " synStatic end");
        }
    }

    public static void main(String[] args) {
        StaticAndClass staticAndClass = new StaticAndClass();
        SynClass synClass = new SynClass();
        SynClass synClass2 = new SynClass();
        //SynStatic synStatic = new SynStatic();

        synClass.start();
        SleepTools.second(1);
        synClass2.start();
        //synStatic.start();
    }

}
