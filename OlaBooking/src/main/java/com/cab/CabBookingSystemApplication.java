package com.cab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CabBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabBookingSystemApplication.class, args);
	}


}
//http://localhost:3000/swagger-ui.html
