package com.concurrent.ch4.readwritelock;

import com.concurrent.tools.SleepTools;

/**
 * @auther xuxq
 * @date 2019/4/26 22:27
 */
public class UseSyn implements GoodsService {

    private GoodsInfo goodsInfo;


    public UseSyn(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public GoodsInfo getNum() {
        SleepTools.ms(5);
        return this.goodsInfo;
    }

    @Override
    public void setNum(int number) {
        SleepTools.ms(5);
        goodsInfo.changeNumber(number);
    }
}
