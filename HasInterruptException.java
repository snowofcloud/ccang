package com.concurrent.ch1.base.safe.end;

/**
 * @auther xuxq
 * @date 2019/4/17 20:30
 * 阻塞方法中抛出InterruptedException异常后，如果需要继续中断，需要手动再中断一次。
 */
public class HasInterruptException {
    private static class UseThread extends Thread {
        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
           while (!isInterrupted()) {
               try {
                   Thread.sleep(20);
               } catch (InterruptedException e) {
                   System.out.println(Thread.currentThread().getName() + " in InterruptedException interrupt flage is " +
                          isInterrupted());
                   interrupt();//资源释放
                   e.printStackTrace();
               }
               System.out.println(Thread.currentThread().getName() + " i am extends Thread");
           }
            System.out.println(Thread.currentThread().getName() + " interrupt flag is " + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseThread useThread = new UseThread("HasInterruptException");
        useThread.start();
        Thread.sleep(10);
        useThread.interrupt();
    }
}
