package com.concurrent.ch4.templatepattern;

/**
 * @auther xuxq
 * @date 2019/4/27 10:32
 */
public class CreamCake extends AbstractCake {
    @Override
    protected void shape() {
        System.out.println("奶油蛋糕造型");
    }

    @Override
    protected void apply() {
        System.out.println("奶油蛋糕造型");
    }

    @Override
    protected void brake() {
        System.out.println("奶油蛋糕造型");
    }
}
