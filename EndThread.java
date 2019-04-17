package com.concurrent.ch1.base.safe.end;

/**
 * @auther xuxq
 * @date 2019/4/17 16:27
 *  继承Thread如何中断线程
 */
public class EndThread {

    private static class UseThread extends Thread {

        public UseThread (String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " interrupt flag = " + isInterrupted());
            //while (true) {  //第一次，同时注释最后一个输出语句，
            //while (!isInterrupted()) {//第二次
              while (!Thread.interrupted()) {//第三次
                    System.out.println(threadName + " is running");
                    System.out.println(threadName + " inner interrupt flag = " + isInterrupted());
            }
            System.out.println(threadName + " interrupt flag = " + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseThread endThread = new UseThread("endThread");
        endThread.start();
        System.out.println("start after!!!");
        Thread.sleep(1);
        endThread.interrupt();//中断线程，其实设置线程的标志位true
    }

}
