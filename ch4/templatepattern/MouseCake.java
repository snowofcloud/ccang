package com.concurrent.ch4.templatepattern;

/**
 * @auther xuxq
 * @date 2019/4/27 10:33
 */
public class MouseCake extends AbstractCake {
    @Override
    protected void shape() {
        System.out.println("慕斯蛋糕造型");
    }

    @Override
    protected void apply() {
        System.out.println("慕斯蛋糕造型");
    }

    @Override
    protected void brake() {
        System.out.println("慕斯蛋糕造型");
    }
}
