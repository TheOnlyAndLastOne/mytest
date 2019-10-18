package com.example.demo.spring.demo2;

/**
 * @author zhi.zhao
 * @date 10/18/2019 5:40 PM
 */
public class DefaultBeanGenerator {
    private DefaultBean defaultBean = new DefaultBean("generator default bean");

    public DefaultBean createDefaultBeanInstance() {
        return defaultBean;
    }
}
