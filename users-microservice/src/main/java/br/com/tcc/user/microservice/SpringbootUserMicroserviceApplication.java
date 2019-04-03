package br.com.tcc.user.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrix
@EnableEurekaClient
@SpringBootApplication
@EnableHystrixDashboard
public class SpringbootUserMicroserviceApplication {

	public static void main(String[] args) {
		 SpringApplication.run(SpringbootUserMicroserviceApplication.class, args);
	}
}
