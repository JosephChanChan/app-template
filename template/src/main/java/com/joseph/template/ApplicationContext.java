package com.joseph.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Joseph
 * @since 2019/7/1 17:17
 */
@MapperScan("com.joseph.template.dao")
@SpringBootApplication(scanBasePackages = {"com.joseph.template", "com.joseph.framework"})
public class ApplicationContext {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationContext.class);
    }

}
