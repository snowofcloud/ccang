package com.concurrent.ch7.transfer.service;

import com.concurrent.ch7.transfer.UserAccount;

/**
 *类说明：不安全的转账动作的实现
 */
public class TrasnferAccount implements ITransfer {
    @Override
    public void transfer(UserAccount from, UserAccount to, int amount)
    		throws InterruptedException {
        synchronized (from){
            System.out.println(Thread.currentThread().getName()
            		+" get"+from.getName());
            Thread.sleep(100);
            synchronized (to){
                System.out.println(Thread.currentThread().getName()
                		+" get"+to.getName());
                from.flyMoney(amount);
                to.addMoney(amount);
            }
        }
    }
}
