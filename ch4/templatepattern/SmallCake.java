package com.concurrent.ch4.templatepattern;

/**
 * @auther xuxq
 * @date 2019/4/27 10:37
 */
public class SmallCake extends AbstractCake {


    private boolean flag = false;

    public void setFlag(boolean shouldApply) {
        flag = shouldApply;
    }

    @Override
    protected boolean shouldApply() {
        return this.flag;
    }

    @Override
    protected void shape() {
        System.out.println("small蛋糕造型");
    }

    @Override
    protected void apply() {
        System.out.println("small蛋糕造型");
    }

    @Override
    protected void brake() {
        System.out.println("small蛋糕造型");
    }
}
