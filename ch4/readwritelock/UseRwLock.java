package com.concurrent.ch4.readwritelock;

import com.concurrent.tools.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @auther xuxq
 * @date 2019/4/26 22:21
 */
public class UseRwLock implements GoodsService {

    private GoodsInfo goodsInfo;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.readLock();

    public UseRwLock(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public UseRwLock() {
        super();
    }

    @Override
    public GoodsInfo getNum() {
        readLock.lock();
        try {
            SleepTools.ms(5);
            return this.goodsInfo;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void setNum(int number) {
        writeLock.lock();
        try {
            SleepTools.ms(5);
            goodsInfo.changeNumber(number);
        } finally {
            writeLock.unlock();
        }
    }
}
