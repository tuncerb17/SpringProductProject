package com.tuncerb;

import com.tuncerb.config.StorageProperties;
import com.tuncerb.services.ImageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SpringProductProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringProductProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ImageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
