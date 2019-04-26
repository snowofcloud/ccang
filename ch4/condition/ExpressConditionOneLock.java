package com.concurrent.ch4.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther xuxq
 * @date 2019/4/26 22:58
 */
public class ExpressConditionOneLock {
    public final static String CITY = "ShangHai";
    private int km;/*快递运输里程数*/
    private String site;/*快递到达地点*/
    private Lock lock = new ReentrantLock();
    private Condition kmCondition = lock.newCondition();
    private Condition siteCondition = lock.newCondition();


    public ExpressConditionOneLock() {
    }

    public ExpressConditionOneLock(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /* 变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理*/
    public synchronized void changeKm(){
        lock.lock();
        try {
            this.km = 101;
            kmCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    /* 变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理*/
    public  synchronized  void changeSite(){
        lock.lock();
        try {
            this.site = "BeiJing";
            siteCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    /*线程等待公里的变化*/
    public synchronized void waitKm(){
        lock.lock();
        try {
            while(this.km<100){
                try {
                    kmCondition.await();
                    System.out.println("Check Site thread["
                            +Thread.currentThread().getId()
                            +"] is be notified");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
        System.out.println("the Km is "+this.km+",I will change db");
    }

    /*线程等待目的地的变化*/
    public synchronized void waitSite(){
        lock.lock();
        try {
            while(this.site.equals(CITY)){//快递到达目的地
                try {
                    siteCondition.await();
                    System.out.println("Check Site thread["+Thread.currentThread().getId()
                            +"] is be notified");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
        System.out.println("the site is "+this.site+",I will call user");
    }
}
