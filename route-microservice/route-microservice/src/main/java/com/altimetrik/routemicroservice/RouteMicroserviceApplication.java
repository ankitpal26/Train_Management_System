package com.altimetrik.routemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RouteMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouteMicroserviceApplication.class, args);
	}

}
