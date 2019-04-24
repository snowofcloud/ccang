package com.concurrent.ch3;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @auther xuxq
 * @date 2019/4/24 22:13
 */
public class UseAtomicReference {
    static AtomicReference<UserInfo> atomicReference;

    public static void main(String[] args) {
        UserInfo peter = new UserInfo("peter", 14);
        atomicReference = new AtomicReference(peter);
        UserInfo bob = new UserInfo("Bob", 14);
        atomicReference.compareAndSet(peter,bob);

        System.out.println(atomicReference.get());
        System.out.println(peter);
    }

    //定义一个实体类
    static class UserInfo {
        private volatile String name;
        private int age;
        public UserInfo(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
