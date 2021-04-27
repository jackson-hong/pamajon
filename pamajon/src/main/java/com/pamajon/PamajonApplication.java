package com.pamajon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class PamajonApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(PamajonApplication.class, args);
	}
}