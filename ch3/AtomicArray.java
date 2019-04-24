package com.concurrent.ch3;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @auther xuxq
 * @date 2019/4/24 21:58
 */
public class AtomicArray {

    static int[] value = new int[]{1,2};
    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        ai.getAndSet(0,3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);
    }













}
