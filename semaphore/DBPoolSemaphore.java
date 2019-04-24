package com.concurrent.ch2.tools.semaphore;

import com.concurrent.ch1.pool.SqlConnectImpl;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * @auther xuxq
 * @date 2019/4/24 19:35
 */
public class DBPoolSemaphore {

    private final static int POOL_SIZE = 10;

    private final Semaphore useful, useless;

    private static LinkedList<Connection> pool = new LinkedList<>();

    static {
        for (int i = 0; i <POOL_SIZE;i++ ) {
            pool.addLast(SqlConnectImpl.fetchConnection());
        }
    }

    public DBPoolSemaphore() {
        this.useful = new Semaphore(10);
        this.useless = new Semaphore(0);
    }

    public void returnConnect(Connection connection) throws InterruptedException {
        if (connection != null) {
            System.out.println("当前有" + useful.getQueueLength() + "个线程等待数据库连接！！" +
                    "可用线程连接数： " + useful.availablePermits());
            useless.acquire();
            synchronized (pool) {
                pool.addLast(connection);
            }
            useful.release();
        }
    }

    public Connection takeConnect() throws InterruptedException {
        useful.acquire();
        Connection connection;
        synchronized (pool) {
            connection = pool.removeFirst();
        }
        useless.release();
        return connection;
    }



}
