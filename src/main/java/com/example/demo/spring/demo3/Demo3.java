package com.example.demo.spring.demo3;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhi.zhao
 * @date 10/18/2019 6:33 PM
 */
@Data
public class Demo3 {
    private String name;
    private Dependency dependency;

    public Demo3(String name, Dependency dependency){
        this.name = name;
        this.dependency = dependency;
    }
}
