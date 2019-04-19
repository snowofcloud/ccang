package com.concurrent.ch1.volatiles;

import com.concurrent.tools.SleepTools;

import java.awt.*;

/**
 * @auther xuxq
 * @date 2019/4/19 22:05
 */
public class VolatileCase {

    private volatile static boolean ready;
    private static int number;

    private static class PrintThread extends Thread {
        @Override
        public void run() {
            System.out.println("PrintThread is running......");
            while (!ready);
            System.out.println("number = " + number);
        }
    }


    public static void main(String[] args) {
        new PrintThread().start();
        SleepTools.second(1);
        number = 51;

        ready = true;
        SleepTools.second(4);
        System.out.println("main is end!");

    }


}
