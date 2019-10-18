package com.example.demo.proxy;

/**
 * @author zhi.zhao
 * @date 10/17/2019 5:06 PM
 */
public class MaleSingleUser extends FemaleUser {
    @Override
    public void addUser() {
        System.out.println("add a male single");
    }

    @Override
    public void deleteUser() {
        System.out.println("delete a male single");
    }
}
