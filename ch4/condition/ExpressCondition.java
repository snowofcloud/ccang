package com.concurrent.ch4.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther xuxq
 * @date 2019/4/26 22:45
 */
public class ExpressCondition {
    public final static String CITY = "ShangHai";
    private int km;/*快递运输里程数*/
    private String site;/*快递到达地点*/

    private Lock kmLock = new ReentrantLock();
    private Lock siteLock = new ReentrantLock();
    private Condition kmCond = kmLock.newCondition();
    private Condition siteCond = siteLock.newCondition();

    public ExpressCondition() {
    }

    public ExpressCondition(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /* 变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理*/
    public synchronized void changeKm(){
        kmLock.lock();
        try {
            this.km = 101;
            kmCond.signal();
        } finally {
            kmLock.unlock();
        }
    }

    /* 变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理*/
    public  synchronized  void changeSite(){
        siteLock.lock();
        try {
            this.site = "BeiJing";
            siteCond.signal();
        } finally {
            siteLock.unlock();
        }
    }

    /*线程等待公里的变化*/
    public synchronized void waitKm(){
        kmLock.lock();
        try {
            while(this.km<100){
                try {
                    kmCond.await();
                    System.out.println("Check Site thread["
                            +Thread.currentThread().getId()
                            +"] is be notified");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            kmLock.unlock();
        }
        System.out.println("the Km is "+this.km+",I will change db");
    }

    /*线程等待目的地的变化*/
    public synchronized void waitSite(){
        siteLock.lock();
        try {
            while(this.site.equals(CITY)){//快递到达目的地
                try {
                    siteCond.await();
                    System.out.println("Check Site thread["+Thread.currentThread().getId()
                            +"] is be notified");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            siteLock.unlock();
        }
        System.out.println("the site is "+this.site+",I will call user");
    }
}
