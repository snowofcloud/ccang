package com.c503.hthj.asoco.dangerchemical.waste.util;

import java.util.concurrent.TimeUnit;

/**
 * @auther xuxq
 * @date 2019/4/11 11:25
 */
public class SleepTools {

    /**
     * 按照秒值休眠
     * @param seconds
     */
    public static final void second(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按照毫秒值休眠
     * @param seconds
     */
    public static final void milliSecond(int seconds){
        try {
            TimeUnit.MILLISECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
