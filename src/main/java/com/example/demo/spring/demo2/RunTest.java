package com.example.demo.spring.demo2;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhi.zhao
 * @date 10/18/2019 5:20 PM
 * bean 工厂注入bean
 */
@Log4j2
public class RunTest {

    @Test
    public void runTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/demo2/demo2.xml");

        //静态工厂对象
        StaticFactoryDemo demo = context.getBean(StaticFactoryDemo.class);
        log.info("common container get:"+demo);

        //一般工厂
        DefaultBean defaultBean = context.getBean(DefaultBean.class);
        log.info("common container get:"+defaultBean);

        /**
         * 一般工厂和静态工厂的区别
         * 静态工程要求工厂方法是static，因为没有对象（这里是指spring容器中没有这个对象）无法调用方法，除非是静态方法（class对象）
         * 然后是一般工厂，一般工厂要先注入工厂方法，调用工厂方法的某个方法生成目标bean
         * 本质上是一样的，通过一个对象的一个方法返回目标对象的实例
         * 注：工厂方法如果需要传参数，通过constructor-arg标签来传参
         */

    }

}
