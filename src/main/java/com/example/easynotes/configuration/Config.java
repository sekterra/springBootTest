package com.example.easynotes.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Rest Service Configuration
 */

@Configuration
public class Config {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			// Cross Origin Resource Sharing 설정
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
								.allowedOrigins("*")
								.allowedMethods("*")  // default : GET, HEAD, POST
								.allowCredentials(false)
								.maxAge(3600);
			}
		};
	}
}
