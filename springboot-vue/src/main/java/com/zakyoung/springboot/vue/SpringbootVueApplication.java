package com.zakyoung.springboot.vue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication(scanBasePackages = "com.zakyoung.springboot.vue")
@MapperScan(basePackages = "com.zakyoung.springboot.vue.dao")
public class SpringbootVueApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootVueApplication.class, args);
	}
}
