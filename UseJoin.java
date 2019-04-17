package com.concurrent.ch1.base;

import com.concurrent.tools.SleepTools;

/**
 * @auther xuxq
 * @date 2019/4/17 23:26
 *  Join方法的使用
 */
public class UseJoin {
    static class Goddness implements Runnable {
        private Thread thread;
        public Goddness(Thread thread) {
            this.thread = thread;
        }
        public Goddness () {

        }

        public void run() {
            System.out.println("Goddness开始排队打饭。。。。");
            try {
                if (thread!=null)
                    thread.join();
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
            SleepTools.second(2);
            System.out.println(Thread.currentThread().getName() + " Goddness打饭完成。");
        }
    }

    static class GoddnessBoyFri implements Runnable {
        public void run() {
            SleepTools.second(2);
            System.out.println("GoddnessBoyFri开始排队打饭。。。。");
            System.out.println(Thread.currentThread().getName() + " GoddnessBoyFri打饭完成。");
        }
    }

    public static void main(String[] args) throws Exception {
        Thread sheZhang = Thread.currentThread();
        GoddnessBoyFri goddnessBoyFri = new GoddnessBoyFri();
        Thread thread = new Thread(goddnessBoyFri);
        Goddness goddness = new Goddness(thread);
//        Goddness goddness = new Goddness();
        Thread g = new Thread(goddness);
        g.start();
        thread.start();

        System.out.println("sheZhang开始打饭。。。。..");
        g.join();//让goddness插队打饭
        SleepTools.second(2);
        System.out.println(Thread.currentThread().getName() + "sheZhang打饭完成.....");

    }

}
