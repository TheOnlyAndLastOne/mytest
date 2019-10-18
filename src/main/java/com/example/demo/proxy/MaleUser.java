package com.example.demo.proxy;

/**
 * @author zhi.zhao
 * @date 10/17/2019 4:49 PM
 */
public class MaleUser implements UserManager {
    @Override
    public void addUser() {
        System.out.println("add a male");
    }

    @Override
    public void deleteUser() {
        System.out.println("delete a male");
    }
}
