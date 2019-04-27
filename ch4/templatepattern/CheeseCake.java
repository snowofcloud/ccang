package com.concurrent.ch4.templatepattern;

/**
 * @auther xuxq
 * @date 2019/4/27 10:30
 */
public class CheeseCake extends AbstractCake {
    @Override
    protected void shape() {
        System.out.println("芝士蛋糕造型");
    }

    @Override
    protected void apply() {
        System.out.println("芝士蛋糕涂抹");
    }

    @Override
    protected void brake() {
        System.out.println("芝士蛋糕烘培");
    }
}
