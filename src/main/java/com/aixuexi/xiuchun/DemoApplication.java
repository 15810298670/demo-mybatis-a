package com.aixuexi.xiuchun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * create on 20/4/1 下午8:41 by jc
 **/
@SpringBootApplication
@MapperScan("com.aixuexi.xiuchun.dao")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
