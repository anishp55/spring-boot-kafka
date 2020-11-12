package com.demo.controllers;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.cfenv.core.CfEnv;
import io.pivotal.cfenv.core.CfCredentials;


@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {		
		System.out.println("===========================================");
		CfEnv cfEnv = new CfEnv();
		CfCredentials kafka_jks = cfEnv.findCredentialsByName(System.getenv("CREDHUB"));
		Map <String, Object> keystores2 = kafka_jks.getMap();


		System.out.println("AKP KAFKA_TRUSTSTORE: " + keystores2.get(System.getenv("KAFKA_TRUSTSTORE")));
		System.out.println("AKP KAFKA_CLIENTSTORE: " + keystores2.get(System.getenv("KAFKA_CLIENTSTORE")));
		System.out.println("===========================================");
		return keystores2.get(System.getenv("KAFKA_TRUSTSTORE")).toString();

	}
}