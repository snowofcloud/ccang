package com.concurrent.ch2.forkjoin.sum;

import com.concurrent.tools.SleepTools;

/**
 * @auther xuxq
 * @date 2019/4/22 22:50
 */
public class SumNormal {
    public static void main(String[] args) {
        int count = 0;
        int[] src = MakeArray.makeArray();

        long start = System.currentTimeMillis();
        for (int i = 0; i < src.length; i++) {
            SleepTools.ms(1);
            count = count + src[i];
        }
        System.out.println("the count is " + count + "spent time: " + (System.currentTimeMillis() - start) + "ms");

    }
}
