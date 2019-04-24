package com.concurrent.ch2.tools.semaphore;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * @auther xuxq
 * @date 2019/4/24 20:00
 */
public class UseExchange {

    private static final Exchanger<Set<String>> exchange = new Exchanger<Set<String>>();


    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
               Set<String> strings = new HashSet<>();

                try {
                    strings = exchange.exchange(strings);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<String> setB = new HashSet<>();//存放数据的容器
                try {
                    /*添加数据
                     * set.add(.....)
                     * set.add(.....)
                     * */
                    setB = exchange.exchange(setB);//交换set
                    /*处理交换后的数据*/
                } catch (InterruptedException e) {
                }
            }
        }).start();

    }
}
