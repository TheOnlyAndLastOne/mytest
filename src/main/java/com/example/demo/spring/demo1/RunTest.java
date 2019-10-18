package com.example.demo.spring.demo1;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author zhi.zhao
 * @date 10/18/2019 4:32 PM
 * 利用XXX.xml来注入bean
 */
@Log4j2
public class RunTest {

    @Test
    public void runTest(){

        /**
         * 1.在xml中可以使用import标签引入其他xml文件，只需要读取一个文件就可以了，或者同时指定多个文件
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/demo1/demo1.xml");

        //指定多文件
        //ApplicationContext context = new ClassPathXmlApplicationContext("spring/demo1/demo1.xml","spring/demo1/demoextra.xml");
        Demo1 demo1 = context.getBean(Demo1.class);
        log.info("common container get:"+demo1);

        /**
         * 2.在bean标签中指定name="xxx1,xxx2,xxx3"或者使用alias标签都可以给一个对象指定多个名字，通过这些名字找到的对象是同一个
         */

        Demo1 demo1_1 = context.getBean("demo1",Demo1.class);
        Demo1 demo1_2 = context.getBean("demo2",Demo1.class);
        Demo1 demo1_3 = context.getBean("demo3",Demo1.class);
        log.info("common container get:"+demo1_1+"~~~"+demo1_2+"~~~"+demo1_3);

        Demo1 demo1_4 = (Demo1) context.getBean("alias1");
        Demo1 demo1_5 = (Demo1) context.getBean("alias3");
        log.info("common container get:"+demo1_4+"~~~"+demo1_5);


        GenericApplicationContext genericContext = new GenericApplicationContext();
        new XmlBeanDefinitionReader(genericContext).loadBeanDefinitions("spring/demo1/demo1.xml");
        /**
         * 刷新容器，否则报错：
         * java.lang.IllegalStateException: org.springframework.context.support.GenericApplicationContext@6156496 has not been refreshed yet
         */
        genericContext.refresh();
        Demo1 genericDemo1 = genericContext.getBean("demo1", Demo1.class);
        log.info("generic container get:"+genericDemo1);


        /**
         * 当spring容器中有多个相同类的对象，只用类对象无法获取到一个准确的对象，需要进行指定
         */
        DemoExtra demoExtra = context.getBean("extra-class2",DemoExtra.class);
        log.info(demoExtra);
    }

}
