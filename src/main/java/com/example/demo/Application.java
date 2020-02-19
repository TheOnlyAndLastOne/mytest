package com.example.demo;


import com.example.demo.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author zhi.zhao
 */
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = {"com.example.demo.elasticsearch.repository"})
@ComponentScan(basePackages = {"com.example.demo"},basePackageClasses={SpringUtil.class})
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
