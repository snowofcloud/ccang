package com.concurrent.ch1.syn;

import com.concurrent.tools.SleepTools;

/**
 * @auther xuxq
 * @date 2019/4/18 11:00
 * 锁的实例不同，
 */
public class DiffInstance {

    private static class InstanceSyn implements Runnable {
        private DiffInstance diffInstance;

        public InstanceSyn(DiffInstance diffInstance) {
            this.diffInstance = diffInstance;
        }

        @Override
        public void run() {
            System.out.println("TestInstance is running..." + diffInstance);
            diffInstance.instance();
        }
    }


    private static class InstanceSyn2 implements Runnable {
        private DiffInstance diffInstance;

        public InstanceSyn2(DiffInstance diffInstance) {
            this.diffInstance = diffInstance;
        }

        @Override
        public void run() {
            System.out.println("TestInstance2 is running..." + diffInstance);
            diffInstance.instance2();
        }
    }



    private synchronized void instance () {
        SleepTools.second(3);
        System.out.println("synInstance is going..." + this.toString());
        SleepTools.second(3);
        System.out.println("synInstance ended " + this.toString());

    }

    private synchronized void instance2 () {
        SleepTools.second(3);
        System.out.println("synInstance2 is going..." + this.toString());
        SleepTools.second(3);
        System.out.println("synInstance2 end " + this.toString());
    }



    public static void main(String[] args) {
        DiffInstance instance1 = new DiffInstance();
        Thread t3 = new Thread(new InstanceSyn(instance1));

        DiffInstance instance2 = new DiffInstance();
        //Thread t4 = new Thread(new InstanceSyn2(instance1));//同一对象实例，只能等一个执行完毕之后，另一个才开始执行
        Thread t4 = new Thread(new InstanceSyn2(instance2));//不同对象实例可以并行执行

        t3.start();
        t4.start();
        SleepTools.second(1);

        //不同对象实例可以并行执行
        /*TestInstance is running...com.concurrent.ch1.syn.DiffInstance@1fad1580
        TestInstance2 is running...com.concurrent.ch1.syn.DiffInstance@b20568f
        synInstance is going...com.concurrent.ch1.syn.DiffInstance@1fad1580
        synInstance2 is going...com.concurrent.ch1.syn.DiffInstance@b20568f
        synInstance ended com.concurrent.ch1.syn.DiffInstance@1fad1580
        synInstance2 end com.concurrent.ch1.syn.DiffInstance@b20568f*/

        //同一对象实例，只能等一个执行完毕之后，另一个才开始执行
        /*TestInstance is running...com.concurrent.ch1.syn.DiffInstance@3d6741a8
        TestInstance2 is running...com.concurrent.ch1.syn.DiffInstance@3d6741a8
        synInstance is going...com.concurrent.ch1.syn.DiffInstance@3d6741a8
        synInstance ended com.concurrent.ch1.syn.DiffInstance@3d6741a8
        synInstance2 is going...com.concurrent.ch1.syn.DiffInstance@3d6741a8
        synInstance2 end com.concurrent.ch1.syn.DiffInstance@3d6741a8*/


    }


}
