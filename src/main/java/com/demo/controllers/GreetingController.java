package com.demo.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.cfenv.core.CfEnv;
import io.pivotal.cfenv.core.CfCredentials;


@RestController
public class GreetingController {

	@GetMapping("/testing")
	public String testing() {
		System.out.println("===========================================");
		// find the credhub we should be using
		CfEnv cfEnv = new CfEnv();
		CfCredentials kafka_jks = cfEnv.findCredentialsByName(System.getenv("CREDHUB"));
		Map <String, Object> keystores = kafka_jks.getMap();

		//extract the cert and write to disk
		byte[] decoded_jksstore = Base64.getDecoder().decode(
										  keystores.get(System.getenv("KAFKA_TRUSTSTORE")).toString()
									);
		File store = new File("./truststore.jks");
		try {
		OutputStream os = new FileOutputStream(store);
		os.write(decoded_jksstore);
		os.close();
		} catch (Exception e) {
			System.out.println("RutRoh: " + e);
		}

		//extract the cert and write to disk
		decoded_jksstore = Base64.getDecoder().decode(
										  keystores.get(System.getenv("KAFKA_CLIENTSTORE")).toString()
									);
		store = new File("./clientstore.jks");
		try {
		OutputStream os = new FileOutputStream(store);
		os.write(decoded_jksstore);
		os.close();
		} catch (Exception e) {
			System.out.println("RutRoh: " + e);
		}

		System.out.println("===========================================");
		return keystores.get(System.getenv("KAFKA_TRUSTSTORE")).toString();

	}
}