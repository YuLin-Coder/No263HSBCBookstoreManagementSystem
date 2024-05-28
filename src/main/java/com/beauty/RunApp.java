package com.beauty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunApp  {

    public static void main(String[] args) {
        SpringApplication.run(RunApp.class, args);
        System.out.println("系统启动成功啦啦啦~~");
    }

}
