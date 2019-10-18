package com.example.demo.spring.demo2;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhi.zhao
 * @date 10/18/2019 5:20 PM
 */
@Data
@AllArgsConstructor
public class StaticFactoryDemo {
    private String name = "static factory";

    private static StaticFactoryDemo clientService = new StaticFactoryDemo("static bean");

    public static StaticFactoryDemo createInstance() {
        return clientService;
    }

}
