package io.microservices.beanvalidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroservicesbeanvalidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesbeanvalidationApplication.class, args);
	}

}
