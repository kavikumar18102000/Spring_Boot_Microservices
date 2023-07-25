package io.arya.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MovieCatalogServiceApplication {

	@Bean
	@LoadBalanced //tells that don't go for the service discovery directly, wait I will give a hint to find the
	// discovery service. And also it does the load balancing. This annotation can be done to the client service or
	// the main point where all the service are connected to fetch the data. In this case movie-catalog is the main
	// service which call other two service. Here movie catalog is the client service.
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}


	@Bean
	public WebClient.Builder getWebclientBuilder(){
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
