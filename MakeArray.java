package com.concurrent.ch2.forkjoin.sum;

import java.util.Random;

/**
 * @auther xuxq
 * @date 2019/4/22 21:53
 */
public class MakeArray {

    //数组长度
    public static final int ARRAY_LENGTH = 40000;
    public static final int THRESHOLD = 45;

    public static int[] makeArray() {
        //new一个随机数发生器
        Random random = new Random();
        int[] result = new int[ARRAY_LENGTH];
        for (int i = 0; i <ARRAY_LENGTH; i++) {
            result[i] = random.nextInt(ARRAY_LENGTH*3);
        }
        return result;
    }


}
