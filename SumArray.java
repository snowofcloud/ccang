package com.concurrent.ch2.forkjoin.sum;

import com.concurrent.tools.SleepTools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @auther xuxq
 * @date 2019/4/22 22:54
 */
public class SumArray {

    private static class SumTask extends RecursiveTask<Integer> {
        //阀值
        private static final int THRESHOLD = MakeArray.ARRAY_LENGTH / 10;
        private int[] src;
        private int fromIndex;
        private int toIndex;

        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Integer compute() {
            if (toIndex - fromIndex < THRESHOLD) {
                int count = 0;
                for (int i = fromIndex; i <= toIndex; i++) {
                    SleepTools.ms(1);
                    count = count +src[i];
                }
                return count;
            } else {
                int mid = (fromIndex + toIndex) / 2;
                SumTask left = new SumTask(src, fromIndex, mid);
                SumTask right = new SumTask(src, mid + 1, toIndex);
                invokeAll(left, right);
                return left.join() + right.join();
            }
        }
    }

    public static void main(String[] args) {
        int[] src = MakeArray.makeArray();
        //new出线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //new出Task的实例
        SumTask innerFind = new SumTask(src, 0, src.length - 1);

        long start = System.currentTimeMillis();

        forkJoinPool.invoke(innerFind);
        System.out.println("the count is " + innerFind.join() + "spent time: " + (System.currentTimeMillis() - start) + "ms");

    }

}
