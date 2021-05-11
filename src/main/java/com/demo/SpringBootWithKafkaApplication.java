package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sh.buildit.credhub.KeyStoreUtils;

@SpringBootApplication
public class SpringBootWithKafkaApplication {

	public static void main(String[] args) {
		KeyStoreUtils.initialize();
		SpringApplication.run(SpringBootWithKafkaApplication.class, args);
	}

}
