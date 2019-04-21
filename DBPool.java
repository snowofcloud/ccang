package com.concurrent.ch1.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @auther xuxq
 * @date 2019/4/21 22:48
 */
public class DBPool {

    //容器，存放连接
    private static LinkedList<Connection> pool = new LinkedList<Connection>();


    //限制连接池的大小
    public DBPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i <initialSize; i++) {
                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }
    //释放连接
    public void releaseConnection (Connection connection) {
        if (connection != null) {
            //锁住连接池，
            synchronized (pool) {
                pool.addLast(connection);
                //通知其他等待连接的线程
                pool.notifyAll();
            }
        }
    }

    //获取连接
    public Connection fetchConnection (long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills <= 0) {//永不超时
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;//超时时刻
                long remaining = mills;//等待超时

                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    //唤醒一次重新计算等待时长
                    remaining = future - System.currentTimeMillis();
                }

                Connection connection = null;
                if (!pool.isEmpty()) {
                    connection = pool.removeFirst();
                }
                return connection;
            }
        }
    }



}
