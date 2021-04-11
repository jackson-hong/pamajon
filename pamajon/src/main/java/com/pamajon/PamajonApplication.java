package com.pamajon;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
@SpringBootApplication
public class PamajonApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(PamajonApplication.class)
				.build()
				.run(args);
		// ServletWebServerFactory bean 누락으로 생기는 문제를 잡아봅시다.
	}

}