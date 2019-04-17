package com.concurrent.ch1.base.safe.end;

/**
 * @auther xuxq
 * @date 2019/4/17 19:52
 * 实现Runnable，如何中断线程
 */
public class EndRunnable {

    private static class UseRunnable implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + " I implements Runnable");
                System.out.println(Thread.currentThread().getName() + " interrupt flag is " +
                        Thread.currentThread().isInterrupted());
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        UseRunnable useRunnable = new UseRunnable();
        Thread endThread = new Thread(useRunnable, "endThread");

        endThread.start();
        Thread.sleep(2);
        endThread.interrupt();
    }


}
