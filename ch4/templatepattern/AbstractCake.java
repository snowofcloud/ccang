package com.concurrent.ch4.templatepattern;

/**
 * @auther xuxq
 * @date 2019/4/27 10:26
 */
public abstract class AbstractCake {

    protected abstract void shape();
    protected abstract void apply();
    protected abstract void brake();

    //模板方法  旧版模板方法
   /* public final void run() {
        this.shape();
        this.apply();
        this.brake();
    }*/

    public final void run() {
        this.shape();
        if (this.shouldApply()) {
            this.apply();
        }
        this.brake();
    }

    protected boolean shouldApply(){
        return true;
    }

}
