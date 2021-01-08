package com.pamajon.pamajon;

import com.pamajon.controller.IndexController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackageClasses = IndexController.class)
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PamajonApplication {

	public static void main(String[] args) {
		SpringApplication.run(PamajonApplication.class, args);
	}

}
