package com.iiht.forum.usermicro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserMicroApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMicroApplication.class, args);
	}

}
