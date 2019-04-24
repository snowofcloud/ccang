package com.concurrent.ch3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther xuxq
 * @date 2019/4/24 22:01
 */
public class UseAtomicInt {
    static AtomicInteger ai = new AtomicInteger(10);

    public static void main(String[] args) {
        ai.getAndIncrement();
        ai.incrementAndGet();
        //ai.compareAndSet();
        ai.addAndGet(24);
    }
}
