package com.myself.shiroproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
@MapperScan("com.myself.shiroproject.dao")
@EnableCaching
public class ShiroprojectApplication{

	public static void main(String[] args) {
		SpringApplication.run(ShiroprojectApplication.class, args);
	}



}
