package com.concurrent.ch7.safeclass;

/**
 * @auther xuxq
 * @date 2019/5/15 10:05
 */
public class StatelessClass {
    //无状态的类
    public int service(int a,int b){

        return a+b;
    }

    public void serviceUser(UserVo user){
        //do sth user
    }

}
