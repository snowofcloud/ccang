package com.concurrent.ch5.bitwise;

import java.io.UnsupportedEncodingException;

/**
 * @auther xuxq
 * @date 2019/5/6 17:10
 */
public class IntToBinary {
    //位运算
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("the 4 is : " + Integer.toBinaryString(4));
        System.out.println("the 6 is : " + Integer.toBinaryString(6));
        //位与&(真真为真 真假为假 假假为假)
        System.out.println("the 4&6 is : " + Integer.toBinaryString(6&4));
        //位或|(真真为真 真假为真 假假为假)
        System.out.println("the 4|6 is : " + Integer.toBinaryString(6|4));
        //位非~
        System.out.println("the ~4 is : " + Integer.toBinaryString(~4));
        //位异或^(真真为假 真假为真 假假为假)
        System.out.println("the 4^6 is : " + Integer.toBinaryString(6^4));
        //有符号右移>>(若正数,高位补0,负数,高位补1)
        System.out.println("the 4>>1 is : " + Integer.toBinaryString(4>>1));
        //有符号左移<<(若正数,高位补0,负数,高位补1)
        System.out.println("the 4<<1 is : " + Integer.toBinaryString(4<<1));
        //无符号右移>>>(不论正负,高位均补0)
        System.out.println("the 234567 is : " + Integer.toBinaryString(234567));
        System.out.println("the 234567>>>4 is : " + Integer.toBinaryString(234567>>>4));
        //无符号右移>>>(不论正负,高位均补0)
        System.out.println("the -4 is : " + Integer.toBinaryString(-4));
        System.out.println("the -4>>>4 is : " + Integer.toBinaryString(-4>>>4));
        System.out.println(Integer.parseInt(Integer.toBinaryString(-4>>>4), 2));

        //取模a % (2^n) 等价于 a & (2^n - 1)
        System.out.println("the 345 % 16 is : " + (345%16)+" or "+(345&(16-1)));

        System.out.println("Mark hashCode is : "+"Mark".hashCode()+"="
                +Integer.toBinaryString("Mark".hashCode()));
        System.out.println("Bill hashCode is : "+"Bill".hashCode()+"="
                +Integer.toBinaryString("Bill".hashCode()));

        //简单输出结果
        /*the 4 is : 100
        the 6 is : 110
        the 4&6 is : 100
        the 4|6 is : 110
        the ~4 is : 11111111111111111111111111111011
        the 4^6 is : 10
        the 4>>1 is : 10
        the 4<<1 is : 1000
        the 234567 is : 111001010001000111
        the 234567>>>4 is : 11100101000100
        the -4 is : 11111111111111111111111111111100
        the -4>>>4 is : 1111111111111111111111111111
        268435455
        the 345 % 16 is : 9 or 9
        Mark hashCode is : 2390765=1001000111101011101101
        Bill hashCode is : 2070567=111111001100000100111*/
    }

}
