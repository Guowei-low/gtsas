package com.gtassignment.sas.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.gtassignment.sas" })
public class AppConfig {

   @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
