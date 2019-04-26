package com.concurrent.ch4.readwritelock;

/**
 * @auther xuxq
 * @date 2019/4/26 22:18
 */
public interface GoodsService {
    public GoodsInfo getNum();//获得商品信息
    public void setNum(int number);//设置商品数量
}
