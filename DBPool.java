package com.c503.hthj.asoco.dangerchemical.waste.util;

import java.sql.Connection;
import java.util.LinkedList;


/**
 * @auther xuxq
 * @date 2019/4/10 20:26
 */
public class DBPool {
    //数据库连接池的容器
    private static LinkedList<Connection> pool = new LinkedList<>();

    public DBPool(int initalSize) {
        if(initalSize>0) {
            for(int i=0;i<initalSize;i++) {
                //SqlConnectImpl一个工具类
                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }

    //在mills时间内还拿不到数据库连接，返回一个null
    public Connection fetchConn(int mills) throws InterruptedException {
        synchronized (pool) {
            if (mills < 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long overtime = System.currentTimeMillis() + mills;
                long remain = mills;
                while (pool.isEmpty() && remain>0) {
                    pool.wait(remain);
                    remain = overtime - System.currentTimeMillis();
                }
                Connection connection = null;
                if (!pool.isEmpty()) {
                    connection = pool.removeFirst();
                }
                return connection;
            }
        }
    }

    //放回数据库连接
    public void releaseConn (Connection connection) {
        if (!pool.isEmpty()) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }



}
