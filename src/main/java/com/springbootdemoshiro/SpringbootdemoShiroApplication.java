package com.springbootdemoshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 */
@SpringBootApplication
@MapperScan("com.springbootdemoshiro.mapper")
public class SpringbootdemoShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoShiroApplication.class, args);
    }

}
