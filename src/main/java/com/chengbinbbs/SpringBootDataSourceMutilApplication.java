package com.chengbinbbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
@MapperScan(basePackages = "com.chengbinbbs.mapper")
public class SpringBootDataSourceMutilApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataSourceMutilApplication.class, args);
	}
}
