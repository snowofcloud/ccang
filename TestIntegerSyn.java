package com.concurrent.ch1.syn;

/**
 * @auther xuxq
 * @date 2019/4/19 21:31
 *  错误的加锁和原因分析
 */
public class TestIntegerSyn {

    public static void main(String[] args) throws InterruptedException {
        Worker worker=new Worker(1);

        for (int i = 0; i <5; i++) {
            new Thread(worker).start();
        }
    }

    private static class Worker implements Runnable {

        private Integer integer;
        private Object object = new Object();

        public Worker(Integer integer) {
            this.integer = integer;
        }

        @Override
        public void run() {
            synchronized (object) {
                Thread thread = Thread.currentThread();
                System.out.println(thread.getName() +  "...@" + System.identityHashCode(integer));
                integer++;
                System.out.println(thread.getName() + "..." + integer + "...@" +System.identityHashCode(integer));

                try {
                    Thread.sleep(3000);
                    //integer.wait(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + "----------" + integer +"----@" +System.identityHashCode(integer));
            }
        }
    }

    /*Thread-1...@1896493143
    Thread-1...2...@2144194566
    Thread-1----------2----@2144194566
    Thread-0...@2144194566
    Thread-0...3...@1872197595
    Thread-0----------3----@1872197595
    Thread-2...@1872197595
    Thread-2...4...@2131079504
    Thread-2----------4----@2131079504
    Thread-4...@2131079504
    Thread-4...5...@1700405589
    Thread-4----------5----@1700405589
    Thread-3...@1700405589
    Thread-3...6...@754568537
    Thread-3----------6----@754568537*/

}
