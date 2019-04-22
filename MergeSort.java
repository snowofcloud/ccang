package com.concurrent.ch2.forkjoin.sort;

import com.concurrent.ch2.forkjoin.sum.MakeArray;

import java.util.Arrays;

/**
 * @auther xuxq
 * @date 2019/4/22 21:53
 */
public class MergeSort {

    public static int[] sort(int[] array) {
        if (array.length <= MakeArray.THRESHOLD) {
            return InsertionSort.sort(array);
        } else {
            //切分数组
            int mid = array.length / 2;
            int[] left = Arrays.copyOfRange(array, 0, mid);
            int[] right = Arrays.copyOfRange(array, mid, array.length);
            return merge(sort(left), sort(right));
        }
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length)
                result[index] = right[j++];
            else if (j >= right.length)
                result[index] = left[i++];
            else if (left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("=============================");
        long start = System.currentTimeMillis();
        MergeSort.sort(MakeArray.makeArray());
        System.out.println(" spent time:" + (System.currentTimeMillis()- start) + "ms");
    }

}
