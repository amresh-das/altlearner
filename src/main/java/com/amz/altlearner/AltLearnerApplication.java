package com.amz.altlearner;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AltLearnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AltLearnerApplication.class, args);
	}

	@Bean
	public AmazonSimpleEmailService amazonSimpleEmailService(@Value("${aws.region}")final String awsRegion) {
		return AmazonSimpleEmailServiceClientBuilder.standard()
				.withRegion(awsRegion).build();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOriginPatterns("http://localhost:4200", "http(s)?://altlearner.org")
						.allowedHeaders("*")
						.allowedMethods("*");
			}
		};
	}
}
