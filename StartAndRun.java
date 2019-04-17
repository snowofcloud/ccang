package com.concurrent.ch1.base;


/**
 * @auther xuxq
 * @date 2019/4/17 20:56
 */
public class StartAndRun {
    private static class UseThread extends Thread {
        @Override
        public void run() {
            int i = 90;
            while(i > 0) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " and now the i =" + i--);
            }
        }
    }

    public static void main(String[] args) {
        UseThread useThread = new UseThread();
        useThread.setName("Start and Run method");
        //useThread.run();//打印的主线程
        useThread.start();
    }

}
