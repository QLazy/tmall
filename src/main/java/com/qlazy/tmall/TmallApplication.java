package com.qlazy.tmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.qlazy.tmall.mapper")
public class TmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmallApplication.class, args);
	}

}
