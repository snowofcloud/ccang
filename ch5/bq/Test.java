package com.concurrent.ch5.bq;

import java.util.concurrent.DelayQueue;

/**
 * @auther xuxq
 * @date 2019/5/13 14:16
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<ItemVo<Order>> itemVos = new DelayQueue<>();//延时队列

        new Thread(new PutOrder(itemVos)).start();
        new Thread(new FetchOrder(itemVos)).start();

        //每隔500毫秒，打印个数字
        for(int i=1;i<15;i++){
            Thread.sleep(500);
            System.out.println(i*500);
        }
    }
}
