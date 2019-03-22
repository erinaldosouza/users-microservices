package br.com.tcc.user.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootUserMicroserviceApplication {

	public static void main(String[] args) {
		 SpringApplication.run(SpringbootUserMicroserviceApplication.class, args);
	}
}
