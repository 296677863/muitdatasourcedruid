package com.lei.muitdatasourcedruid.dynamic.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lei.muitdatasourcedruid.dynamic.datasource.mapper")
public class MuitdatasourcedruidApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuitdatasourcedruidApplication.class, args);
		
	}
}
