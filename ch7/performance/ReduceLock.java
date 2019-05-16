package com.concurrent.ch7.performance;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @auther xuxq
 * @date 2019/5/16 9:38
 */
public class ReduceLock {
    //缩小锁的范围
    private Map<String, String> map = new HashMap<>();

    public synchronized boolean isMath(String name, String regexp){
        String key = "user." + name;
        String job = map.get(key);

        if (job == null)
            return false;
        else
            return Pattern.matches(regexp,job);
    }

    private boolean isMathReduce(String name, String regexp) {
        String key = "user." + name;
        String job ;
        synchronized (this){
            job = map.get(key);
        }
        if (job == null)
            return false;
        else
            return Pattern.matches(regexp,job);
    }

}
