package com.concurrent.ch2.tools.semaphore;

import com.concurrent.tools.SleepTools;

import java.sql.Connection;
import java.util.Random;

/**
 * @auther xuxq
 * @date 2019/4/24 19:51
 */
public class AppTest {

    private static DBPoolSemaphore dbPoolSemaphore = new DBPoolSemaphore();

    private static class BusiThread extends Thread {
        @Override
        public void run() {
            Random random = new Random();
            long start = System.currentTimeMillis();

            try {
                Connection connection = dbPoolSemaphore.takeConnect();
                System.out.println("Thread_"+Thread.currentThread().getId()
                        +"_获取数据库连接共耗时【"+(System.currentTimeMillis()-start)+"】ms.");
                SleepTools.ms(100 + random.nextInt(100));
                System.out.println("查询数据完成，归还连接");
                dbPoolSemaphore.returnConnect(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0;i < 50; i++) {
            Thread thread = new BusiThread();
            thread.start();
        }
    }

}
