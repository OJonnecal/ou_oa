package com.jjou.ouOffice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.jjou.ouOffice.mapper"})
@SpringBootApplication
public class OuOfficeApplication {
    public static void main(String[] args) {
        SpringApplication.run(OuOfficeApplication.class, args);
    }
}
