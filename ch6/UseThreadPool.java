package com.concurrent.ch6;

import com.concurrent.tools.SleepTools;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @auther xuxq
 * @date 2019/5/15 8:55
 */
public class UseThreadPool {
    /*没有返回值*/
    static class Worker implements Runnable{
        private String taskName;
        private Random random = new Random();

        public Worker(String taskName) {
            this.taskName = taskName;
        }

        public String getName() {
            return taskName;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "process the task:" + taskName);
            SleepTools.ms(random.nextInt(100)*5);
        }
    }
    /*有返回值*/
    static class CallWork implements Callable<String> {
        private String taskName;
        private Random random = new Random();

        public CallWork(String taskName) {
            this.taskName = taskName;
        }

        public String getName() {
            return taskName;
        }

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName() + "process the task:" +taskName);
            return Thread.currentThread().getName() + "::::" +random.nextInt(100)*5;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Runtime.getRuntime().availableProcessors();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();
        ExecutorService threadPool2 = Executors.newCachedThreadPool();
        ExecutorService threadPool3 = Executors.newWorkStealingPool();

        for (int i = 0; i <= 6; i++) {
            Worker work = new Worker("worker" + i);
            System.out.println("A new task has been added : " + work.getName());
            threadPool.execute(work);
        }

        for (int i = 0; i <= 6; i++) {
            CallWork callWork = new CallWork("worker" + i);
            System.out.println("A new task has been added : " + callWork.getName());
            Future<String> submit = threadPool.submit(callWork);
            System.out.println(submit.get());
        }
        threadPool.shutdown();
        threadPool.shutdownNow();
    }

}
