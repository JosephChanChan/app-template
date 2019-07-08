package com.byb.sznews;

import com.byb.framework.config.RedisConfigure;
import com.byb.framework.config.ServletConfigure;
import com.byb.sznews.config.ShiroConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author Joseph
 * @since 2019/7/1 17:17
 */
@ComponentScan({
        "com.byb.sznews",
        "com.byb.service",
        "com.byb.service.impl",
        "com.byb.dao",
        "com.byb.framework"
})
/*@Import({
        ServletConfigure.class,
        RedisConfigure.class*//*,
        ShiroConfiguration.class*//*
})*/
@MapperScan("com.byb.dao")
@SpringBootApplication
public class ApplicationContext extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationContext.class);
    }

}
