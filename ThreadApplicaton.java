package com.enjoy.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadApplicaton {

    /*extends Thread*/
    //第一种新启线程方式

    /*implements Runnable*/
    //第二种新启线程方式
    private static class UseRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println("Runnable是没有返回值的，");
        }
    }

    /*implements Callable*/
    //第三种新启线程方式
    private static class UseCallable implements Callable{
        @Override
        public String call() throws Exception {
            System.out.println("Callable是有返回值的，");

            return "UseCallable";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        UseRunnable useRunnable = new UseRunnable();
        new Thread(useRunnable).start();

        UseCallable useCallable = new UseCallable();
        FutureTask<String> stringFutureTask = new FutureTask<>(useCallable);
        new Thread(stringFutureTask).start();
        System.out.println(stringFutureTask.get());

    }


}
