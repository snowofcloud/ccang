package com.concurrent.ch2.forkjoin.sort;

import com.concurrent.ch2.forkjoin.sum.MakeArray;

/**
 * @auther xuxq
 * @date 2019/4/22 21:45
 */
public class InsertionSort {

    public static int[] sort(int[] array) {
        if (array.length  == 0)
            return array;
        int currentValue;
        for (int i = 0; i <array.length - 1; i++) {
            int preIndex = i;//已被排序的数据索引
            currentValue = array[preIndex + 1];

            while (preIndex >= 0 && currentValue < array[preIndex]) {
                array[preIndex + 1] = currentValue;
                preIndex--;
            }
            array[preIndex + 1] = currentValue;
        }
        return array;
    }

    public static void main(String[] args) {
        System.out.println("================================");
        InsertionSort.sort(MakeArray.makeArray());
    }

}
