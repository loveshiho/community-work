package com.akai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.akai.**.mapper")
public class CommunityManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunityManagementApplication.class, args);
	}

}
