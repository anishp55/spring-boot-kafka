package com.demo.controllers;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.cfenv.core.CfEnv;
import io.pivotal.cfenv.core.CfService;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {		
		System.out.println("===========================================");
		CfEnv cfEnv = new CfEnv();
		CfService kafka_credhub = cfEnv.findServiceByName(System.getenv("CREDHUB"));
		System.out.println("keystore from credhub: " + kafka_credhub.getMap()) ;
		// add code to pull data from our credhub service instance
		String vcapServices = System.getenv("VCAP_SERVICES");
		JsonParser springParser = JsonParserFactory.getJsonParser();
		Map<String, Object> map = springParser.parseMap(vcapServices);
		
		

		String mapArray[] = new String[map.size()];
		System.out.println("Items found: " + mapArray.length);

		int i = 0;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println("FOUND KEY: " + entry.getKey() + " = " + entry.getValue());
			i++;
		}


		System.out.println("AKP " + map.get("credhub"));
		// System.out.println("AKP " + map );
		System.out.println("AKP " + System.getenv("KAFKA_TRUSTSTORE"));
		System.out.println("AKP " + System.getenv("KAFKA_CLIENTSTORE"));
		System.out.println("===========================================");
		return System.getenv("VCAP_SERVICES");

	}
}