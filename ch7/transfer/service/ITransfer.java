package com.concurrent.ch7.transfer.service;

import com.concurrent.ch7.transfer.UserAccount;

/**
 *
 *类说明：银行转账动作接口
 */
public interface ITransfer {
    void transfer(UserAccount from, UserAccount to, int amount)
    		throws InterruptedException;
}
