package com.demo.controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.util.Base64;
import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
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

		char[] password = {'1','2','3','4','5','6'};

		//extract the jks and write to disk, and load into a keyStore
		byte[] decoded_jksstore = Base64.getDecoder().decode(
			                                      keystores.get(System.getenv("KAFKA_CLIENTSTORE")).toString()
									);
		File store = new File("./truststore.jks");
		try {
		OutputStream os = new FileOutputStream(store);
		os.write(decoded_jksstore);
		os.close();
		KeyStore trustStore = KeyStore.getInstance("PKCS12");
		InputStream trustStoreStream = new ByteArrayInputStream(decoded_jksstore);
		trustStore.load(trustStoreStream,password);
		} catch (Exception e) {
			System.out.println("RutRoh: " + e);
		}



		//extract the second and write to disk, and load into a keyStore
		decoded_jksstore = Base64.getDecoder().decode(
                                           keystores.get(System.getenv("KAFKA_CLIENTSTORE")).toString()
									);
		store = new File("./clientstore.jks");
		try {
		// OutputStream os = new FileOutputStream(store);
		// os.write(decoded_jksstore);
		// os.close();
		KeyStore clientStore = KeyStore.getInstance("PKCS12");
		InputStream clientStoreStream = new ByteArrayInputStream(decoded_jksstore);
		clientStore.load(clientStoreStream,password);
		} catch (Exception e) {
			System.out.println("RutRoh: " + e);
		}

		System.out.println("===========================================");
		return '{"msg" : "keystore extraction completed"}';

	}
}