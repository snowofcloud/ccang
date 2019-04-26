package com.concurrent.ch4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther xuxq
 * @date 2019/4/26 19:34
 */
public class LockCase {

    private Lock lock = new ReentrantLock();
    private int age  = 100000;

    private static class TestThread extends Thread {
        private LockCase lockCase;

        public TestThread (LockCase lockCase, String name) {
            super(name);
            this.lockCase = lockCase;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                lockCase.test();
                System.out.println(Thread.currentThread().getName()
                    + " age = " + lockCase.getAge());
            }
        }
    }

    public void test() {
        lock.lock();
        try {
            age++;
        } finally {
            lock.unlock();
        }
    }

    public void test2(){
        lock.lock();
        try {
            age--;
        } finally {
            lock.unlock();
        }
    }

    public int getAge(){
        return age;
    }

    public static void main(String[] args) {
        LockCase lockCase = new LockCase();
        TestThread endThread = new TestThread(lockCase, "endThread");
        endThread.start();
        for (int i = 0; i <100000; i++) {
            lockCase.test2();
        }
        System.out.println(Thread.currentThread().getName() + " age = " + lockCase.getAge());
    }


}
