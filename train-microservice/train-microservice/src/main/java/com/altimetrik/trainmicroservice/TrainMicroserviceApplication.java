package com.altimetrik.trainmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TrainMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainMicroserviceApplication.class, args);
	}

}
