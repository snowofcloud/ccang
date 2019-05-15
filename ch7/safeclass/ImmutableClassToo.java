package com.concurrent.ch7.safeclass;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther xuxq
 * @date 2019/5/15 9:54
 */
public class ImmutableClassToo {
    private List<Integer> list = new ArrayList<>(3);

    public ImmutableClassToo() {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    public boolean isContain(int i){
        return list.contains(i);
    }
}
