package com.concurrent.ch1.thread.local;

/**
 * @auther xuxq
 * @date 2019/4/19 22:41
 */
public class UseThreadLocal {

    private static ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    private static ThreadLocal<String> stringThreadLocal;

    //运行3个线程
    public void StartThreadArray(){
        Thread[] threads = new Thread[3];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new TestThread(i));
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

    }


    //测试线程
    public static class TestThread implements Runnable {

        private int id;

        public TestThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " : start");
            Integer s = integerThreadLocal.get();
            s = s + id;
            integerThreadLocal.set(s);
            System.out.println(Thread.currentThread().getName() + ":" + integerThreadLocal.get());
        }
    }

    public static void main(String[] args) {
        UseThreadLocal useThreadLocal = new UseThreadLocal();
        useThreadLocal.StartThreadArray();
    }

}
