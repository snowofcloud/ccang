package com.c503.hthj.asoco.dangerchemical.waste.util;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther xuxq
 * @date 2019/4/10 20:51
 */
public class DBPoolTest {

    static DBPool pool = new DBPool(10);
    //控制器：控制主线程将会等待所有worker结束后才能继续执行
    static CountDownLatch end;

    public static void main(String[] args) throws Exception {
        //线程数量
        int threadCount = 50;
        end = new CountDownLatch(threadCount);

        int count = 20;//每个线程的操作次数
        AtomicInteger g = new AtomicInteger();//计数器：统计可以拿到连接的线程
        AtomicInteger notG = new AtomicInteger();//计数器：统计没有拿到连接的线程
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new Worker(count,g,notG),"worker_"+i);
            thread.start();
        }

        end.await();//主线程再次等待
        System.out.println("总共尝试了: " + (threadCount * count));
        System.out.println("拿到连接的次数：  " + g);
        System.out.println("没能连接的次数： " + notG);

    }


    static class Worker implements Runnable {
        int count;
        AtomicInteger g;
        AtomicInteger notG;

        public Worker(int count, AtomicInteger got,
                      AtomicInteger notGot) {
            this.count = count;
            this.g = g;
            this.notG = notG;
        }

        public void run() {
            while (count > 0) {
                try {
                    // 从线程池中获取连接，如果1000ms内无法获取到，将会返回null
                    // 分别统计连接获取的数量got和未获取到的数量notGot
                    Connection connection = pool.fetchConn(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConn(connection);
                            g.incrementAndGet();
                        }
                    } else {
                        notG.incrementAndGet();
                        System.out.println(Thread.currentThread().getName()
                                +"等待超时!");
                    }
                } catch (Exception ex) {
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }


}
