package com.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.spring.cloud"})
@EnableTransactionManagement
@EnableAsync
public class SpringBootCloudCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCloudCoreApplication.class, args);
	}

}
