package com.concurrent.ch3.answer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther xuxq
 * @date 2019/4/26 23:31
 */
public class HalfAtomicInt {
    private AtomicInteger atomicI = new AtomicInteger(0);

    /*请完成这个递增方法*/
    public void increament() {
        for (;;) {
            int i = getCount();
            boolean suc = compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }

    public int getCount() {
        return atomicI.get();
    }

    public boolean compareAndSet(int oldValue,int newValue){
        return atomicI.compareAndSet(oldValue,newValue);
    }

    public static void main(String[] args) throws InterruptedException {
        HalfAtomicInt halfAtomicInt = new HalfAtomicInt();
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<1000;i++){
                        halfAtomicInt.increament();
                    }
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(halfAtomicInt.getCount());
    }
}
