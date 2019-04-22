package com.concurrent.ch1.pool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther xuxq
 * @date 2019/4/21 23:14
 */
public class DBPoolTest {

    static DBPool dbPool = new DBPool(10);
    //控制器：控制main线程将会等待所有Worker结束后才能继续执行
    static CountDownLatch end;

    public static void main(String[] args) throws Exception {
        //线程数量
        int threadCount = 50;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();//计数器：拿到连接的线程
        AtomicInteger notGot = new AtomicInteger();//计数器：未拿到连接的线程
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new Worker(count,got,notGot),"worker_" + i);
            thread.start();
        }
        end.await();//main再此等待
        System.out.println("总共尝试了：" + (threadCount * count));
        System.out.println("拿到连接的次数：" + got);
        System.out.println("未拿到连接的次数：" + notGot);


    }

    static class Worker implements Runnable {

        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public Worker(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            while (count > 0) {
                try {
                    Connection connection = dbPool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            dbPool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                        System.out.println(Thread.currentThread().getName() + "等待超时");
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }

}
