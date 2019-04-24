package com.concurrent.ch2.tools;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * @auther xuxq
 * @date 2019/4/24 18:54
 */
public class UseCyclicBarrier {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(4,new CollectThread());

    //存放子线程的容器
    private static ConcurrentHashMap<String, Long> resultMap = new ConcurrentHashMap<>();

    public static void main(String args[]) {
        for (int i = 0; i < 4; i++) {
            Thread threads = new Thread(new SubThread());
            threads.start();
        }
    }

    //汇总任务
    private static class CollectThread implements Runnable {
        @Override
        public void run() {
            StringBuilder result = new StringBuilder();
            for (Map.Entry<String, Long> workResult: resultMap.entrySet()) {
                result.append("[" + workResult.getValue() + "]");
            }
            System.out.println(" the result = " + result);
            System.out.println("do the business..............");
        }
    }

    //相互等待的子线程
    private static class SubThread implements Runnable {
        @Override
        public void run() {
            long id = Thread.currentThread().getId();
            try {
                resultMap.put(Thread.currentThread().getId() + "",id);
                Thread.sleep(1000 + id);
                System.out.println("Thread_" + id + "....do something");
                cyclicBarrier.await();
                Thread.sleep(1000 + id);
                System.out.println("Thread_" + id + "....do its business");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }



























}
