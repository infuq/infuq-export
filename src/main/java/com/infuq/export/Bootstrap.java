package com.infuq.export;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = { MongoAutoConfiguration.class,
//        RedisAutoConfiguration.class,
//        RedissonAutoConfiguration.class,

//        MybatisPlusAutoConfiguration.class,
//        DynamicDataSourceAutoConfiguration.class,
//        DataSourceAutoConfiguration.class
})
@EnableTransactionManagement
@ServletComponentScan(basePackages = {"com.infuq.springboot"})
public class Bootstrap {

    public static void main(String[] args) {

        SpringApplication.run(Bootstrap.class, args);

    }

}
