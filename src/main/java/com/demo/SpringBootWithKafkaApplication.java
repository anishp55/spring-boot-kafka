package com.demo;

import com.mmccaskill.KeyStoreUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootWithKafkaApplication {

	public static void main(String[] args) {
		KeyStoreUtils.initialize();
		SpringApplication.run(SpringBootWithKafkaApplication.class, args);
	}

}
