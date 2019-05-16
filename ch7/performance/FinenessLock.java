package com.concurrent.ch7.performance;

import java.util.HashSet;
import java.util.Set;

/**
 * @auther xuxq
 * @date 2019/5/16 9:27
 */
public class FinenessLock {
    //锁分离
    private final Set<String> users = new HashSet<>();
    private final Set<String> queries = new HashSet<>();

    public synchronized void addUser(String u){
        users.add(u);
    }

    public synchronized void addQuery(String q){
        queries.add(q);
    }

    public synchronized void removeUser(String u){
        users.remove(u);
    }

    public synchronized void removeQuery(String q) {
        queries.remove(q);
    }

    public void addUserDiv(String u){
        synchronized (users) {
            users.add(u);
        }
    }

    public void addQueryDiv(String q){
        synchronized (queries) {
            queries.add(q);
        }
    }


}
