package com.concurrent.ch5.bq;

import java.util.concurrent.DelayQueue;

/**
 * @auther xuxq
 * @date 2019/5/13 14:09
 */
public class FetchOrder implements Runnable {

    private DelayQueue<ItemVo<Order>> queue;

    public FetchOrder(DelayQueue<ItemVo<Order>> queue){
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            ItemVo<Order> take = queue.take();
            Order order = (Order) take.getData();
            System.out.println("Get From Queue:"+"data="
                    +order.getOrderNo()+";"+order.getOrderMoney());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
