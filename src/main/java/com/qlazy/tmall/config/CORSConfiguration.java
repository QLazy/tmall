package com.qlazy.tmall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class CORSConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//使全部请求都能跨域
		registry.addMapping("/**")
				.allowedHeaders("*")
				.allowedOrigins("*")
				.allowedMethods("*");
	}

}
