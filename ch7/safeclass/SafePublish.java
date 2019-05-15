package com.concurrent.ch7.safeclass;

/**
 * @auther xuxq
 * @date 2019/5/15 10:07
 */
public class SafePublish {
    //安全发布
    private int i;

    public SafePublish() {
        i = 2;
    }

    public int getI() {
        return i;
    }

    public static void main(String[] args) {
        SafePublish safePublish = new SafePublish();
        int j = safePublish.getI();
        System.out.println("before j="+j);
        j = 3;
        System.out.println("after j="+j);
        System.out.println("getI = "+safePublish.getI());
    }
}
