package com.cjj.pms;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 陈建军
 * @version 1.0
 */
@SpringBootApplication
@MapperScan("com.cjj.pms.mapper")
public class PMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(PMSApplication.class,args);
    }
}
