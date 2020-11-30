package com.example.csy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.example.csy.dao")
public class CsyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsyApplication.class, args);
    }

}
