package com.example.demo.spring.demo3;

import com.example.demo.Application;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhi.zhao
 * @date 10/18/2019 6:13 PM
 * 利用构造函数实现依赖注入
 */
@Log4j2
public class RunTest {

    @Test
    public void runTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/demo3/demo3.xml");
        Demo3 bean = context.getBean(Demo3.class);
        log.info(bean);
    }

}
