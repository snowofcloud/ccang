package com.concurrent.ch4.templatepattern;

/**
 * @auther xuxq
 * @date 2019/4/27 10:34
 */
public class MakeCake {

    public static void main(String args[]) {
        AbstractCake cake1 = new CheeseCake();//芝士蛋糕
        AbstractCake cake2 = new CreamCake();//奶油蛋糕
        AbstractCake cake3 = new MouseCake();//慕斯蛋糕

        cake1.run();

        AbstractCake cake  =new SmallCake();
        cake.run();
    }

}
