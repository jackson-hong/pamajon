package com.pamajon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class PamajonApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
//		new SpringApplicationBuilder(PamajonApplication.class)
//				.build()
//				.run(args);
		// ServletWebServerFactory bean 누락으로 생기는 문제를 잡아봅시다.
		SpringApplication.run(PamajonApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<PamajonApplication> applicationClass = PamajonApplication.class;
}