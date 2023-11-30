package com.memo.intejmemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.memo.member.repository", "com.memo.intejmemo.*"})
public class IntejMemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(IntejMemoApplication.class, args);
	}
}
