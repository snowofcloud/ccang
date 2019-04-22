package com.concurrent.ch2.forkjoin.sort;

import com.concurrent.ch2.forkjoin.sum.MakeArray;
import com.sun.org.apache.regexp.internal.RE;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @auther xuxq
 * @date 2019/4/22 21:40
 */
public class FkSort {

    private static class SumTask extends RecursiveTask<int[]> {

        private final static int THRESHOLD = 2;
        private int[] src;
        public SumTask(int[] src) {
            this.src = src;
        }

        @Override
        protected int[] compute() {

            if (src.length <= THRESHOLD) {
                return InsertionSort.sort(src);
            } else {
                int mid = src.length/2;
                SumTask leftTask = new SumTask(Arrays.copyOfRange(src, 0, mid));
                SumTask rightTask = new SumTask(Arrays.copyOfRange(src, mid, src.length));
                invokeAll(leftTask, rightTask);

                int[] leftResult = leftTask.join();
                int[] rightResult = rightTask.join();
                return MergeSort.merge(leftResult, rightResult);
            }
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int[] src = MakeArray.makeArray();

        SumTask innerFind = new SumTask(src);

        long start = System.currentTimeMillis();

        forkJoinPool.invoke(innerFind);

        System.out.println("spent time:" + (System.currentTimeMillis() - start) + "ms");


    }

}
