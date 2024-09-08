package com.adalbertosolf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ProjectApiApplication {

	/*
	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}
	*/
	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder.baseUrl("http://viacep.com.br")
		.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		.build();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectApiApplication.class, args);
		//System.out.println("Hello");
	}

}
