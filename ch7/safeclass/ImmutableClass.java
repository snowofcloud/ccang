package com.concurrent.ch7.safeclass;

/**
 * @auther xuxq
 * @date 2019/5/15 9:55
 */
public class ImmutableClass {
    private final int a;
    private final UserVo user = new UserVo();//不安全

    public int getA() {
        return a;
    }

    public UserVo getUser() {
        return user;
    }


    public ImmutableClass(int a) {
        this.a = a;
    }

    public static class User{
        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
