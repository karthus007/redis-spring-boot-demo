package com.andin.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.*")
@MapperScan("com.andin.mapper")
public class MyAdmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyAdmailApplication.class, args);
	}

}
