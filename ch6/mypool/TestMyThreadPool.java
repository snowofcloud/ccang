package com.concurrent.ch6.mypool;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TestMyThreadPool {
    public static void main(String[] args) throws InterruptedException {
        MyThreadPool2 threadPool2 = new MyThreadPool2(0,3);
        threadPool2.execute(new MyTasks("TaskA"));
        threadPool2.execute(new MyTasks("TaskB"));
        threadPool2.execute(new MyTasks("TaskC"));
        threadPool2.execute(new MyTasks("TaskD"));
        threadPool2.execute(new MyTasks("TaskE"));
        System.out.println(threadPool2);
        Thread.sleep(10000);
        threadPool2.destory();
        System.out.println(threadPool2);

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        Executors.newScheduledThreadPool()

    }

    static class MyTasks implements Runnable{

        private String name;
        private Random r = new Random();
        public MyTasks(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }
        @Override
        public void run() {

            try {
                Thread.sleep(r.nextInt(1000) +2000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getId() + "sleep InterruptedException" +
                        Thread.currentThread().isInterrupted());
            }
            System.out.println("任务" + name + "完成");
        }
    }


}
