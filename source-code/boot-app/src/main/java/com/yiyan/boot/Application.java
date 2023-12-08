package com.yiyan.boot;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 */
@SpringBootApplication
@Configurable
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
