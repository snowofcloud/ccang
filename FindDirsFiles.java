package com.concurrent.ch2.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @auther xuxq
 * @date 2019/4/22 21:39
 */
public class FindDirsFiles extends RecursiveAction {

    private File path;
    public FindDirsFiles(File path) {
        this.path = path;
    }

    @Override
    protected void compute() {
        List<FindDirsFiles> findDirsFiles = new ArrayList<>();
       File[] files = path.listFiles();
       if (files != null) {
            for (File file: files) {
                if (file.isDirectory()) {
                    findDirsFiles.add(new FindDirsFiles(file));
                }else {
                    if (file.getAbsolutePath().endsWith("text")) {
                        System.out.println("文件：" + file.getAbsolutePath());
                    }
                }
            }
            if (!findDirsFiles.isEmpty()) {
                for (FindDirsFiles findDirsFiles1: invokeAll(findDirsFiles)) {
                    findDirsFiles1.join();
                }
            }
       }
    }

    public static void main(String[] args) {
        try {
            //用一个ForkJoinPool实例调度总任务
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            FindDirsFiles task = new FindDirsFiles(new File("K:/tops"));

            //异步提交
            forkJoinPool.execute(task);

            //主线程做自己的业务工作
            System.out.println("task is running...");
            Thread.sleep(1);
            int otherWork = 0;
            for (int i = 0; i <100; i++) {
                otherWork = otherWork+i;
            }
            System.out.println("main Thread done sth...., otherWork=s" + otherWork);
            task.join();//阻塞方法
            System.out.println("Task end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

