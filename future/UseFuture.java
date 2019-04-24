package com.concurrent.ch2.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @auther xuxq
 * @date 2019/4/24 23:12
 */
public class UseFuture {

    private static class UseCallable implements Callable<Integer> {
        private int sum;

        @Override
        public Integer call() throws Exception {
            System.out.println("Callable子线程开始计算！");

            for (int i = 0; i <5000; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Callable子线程计算任务中断！");
                    return null;
                }
                sum = sum + i;
                System.out.println("sum=" + sum);
            }
            System.out.println("Callable子线程计算结束！" + sum);
            return sum;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        UseCallable useCallable = new UseCallable();
        //包装2
        FutureTask<Integer> futureTask = new FutureTask<>(useCallable);
        Random random = new Random();
        new Thread(futureTask).start();

        Thread.sleep(1);
        if (random.nextInt(100) > 50) {
            System.out.println("Get UseCallable result = "+futureTask.get());
        } else {
            System.out.println("Cancel................. ");
            futureTask.cancel(true);
        }

    }

}
