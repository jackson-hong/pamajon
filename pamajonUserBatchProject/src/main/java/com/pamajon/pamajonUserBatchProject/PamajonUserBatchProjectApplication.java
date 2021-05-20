package com.pamajon.pamajonUserBatchProject;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class PamajonUserBatchProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PamajonUserBatchProjectApplication.class, args);
	}

}
