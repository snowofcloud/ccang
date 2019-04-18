package com.concurrent.ch1.syn;

/**
 * @auther xuxq
 * @date 2019/4/18 8:57
 */
public class SynTest {

    private long count  = 0;
    private Object obj = new Object();//代表一个锁

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    //yong在同步块上
    //public void increCount () {  //未加锁
    //public synchronized void increCount () {
    public void increCount () {
        synchronized (obj) {//代码里面加锁
            count++;
        }
    }
    //用在方法上
    public synchronized void increCount2() {
        count++;
    }
    //进行累加
    public void increCount3(){
        synchronized (this){
            count++;
        }
    }

    //线程
    private static class Count extends Thread{
        private SynTest simplOper;
        public Count(SynTest simplOper) {
            this.simplOper = simplOper;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                simplOper.increCount();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynTest simplOper = new SynTest();
        //启动连个线程
        Count count1 = new Count(simplOper);
        Count count2 = new Count(simplOper);
        count1.start();
        count2.start();
        Thread.sleep(50);
        System.out.println(simplOper.count);

    }



}
