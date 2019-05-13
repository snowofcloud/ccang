package com.concurrent.ch5.bq;

/**
 * @auther xuxq
 * @date 2019/5/13 14:05
 */
public class Order {
    private final String orderNo;
    private final double orderMoney;

    public Order(String orderNo, double orderMoney) {
        super();
        this.orderNo = orderNo;
        this.orderMoney = orderMoney;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public double getOrderMoney() {
        return orderMoney;
    }
}
