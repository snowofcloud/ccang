package com.concurrent.ch4.condition;

/**
 * @auther xuxq
 * @date 2019/4/26 23:05
 */
public class TestCondition {

    private static ExpressCondition expressCondition = new ExpressCondition(0,ExpressCondition.CITY);

    private static class CheckKm extends Thread {
        @Override
        public void run() {
            expressCondition.waitKm();
        }
    }

    private static class CheckSite extends Thread {
        @Override
        public void run() {
            expressCondition.waitSite();
        }
    }

    public static void main (String args[]) throws InterruptedException {
        for (int i = 0; i <3; i++) {
            new CheckSite().start();
        }
        for (int i = 0; i <3; i++) {
            new CheckKm().start();
        }
        Thread.sleep(10);
        expressCondition.changeKm();
    }

}
