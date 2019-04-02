package com.enjoy.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class OnlyMain {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo :threadInfos) {
            System.out.println("["+ threadInfo.getThreadId() +"]" + "" + threadInfo.getThreadName());
        }
        //[6]Monitor Ctrl-Break
        //[5]Attach Listener 获取当前程序运行的各种信息
        //[4]Signal Dispatcher 分发给虚拟机信号的线程
        //[3]Finalizer 调用对象的Finalizer方法
        //[2]Reference Handler:清除引用
        //[1]main
    }
}
