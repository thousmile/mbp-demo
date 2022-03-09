package com.xaaef.mbp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xaaef.mbp.mapper")
public class MbpDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbpDemoApplication.class, args);
    }

}
