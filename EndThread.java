package com.enjoy.utils;

public class EndThread {
    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new UseThread("endThread");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();
    }

    private static class UseThread extends Thread{
        public UseThread(String name){
            super(name);
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            while (true) {
                System.out.println(name + "is run!");
            }
            //System.out.println(name+" interrput flag is "+isInterrupted());
        }
    }

}
