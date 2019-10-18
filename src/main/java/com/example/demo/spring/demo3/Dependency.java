package com.example.demo.spring.demo3;

import lombok.Data;

/**
 * @author zhi.zhao
 * @date 10/18/2019 6:33 PM
 */
@Data
public class Dependency {
    private String name = "dependency";
    private Dependency2 dependency2;
}
