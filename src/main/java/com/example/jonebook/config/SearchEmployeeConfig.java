package com.example.jonebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.jonebook.services.search.CustomQuerySearchEmployee;
import com.example.jonebook.services.search.SearchEmployee;

@Configuration
public class SearchEmployeeConfig {
    @Bean
    public SearchEmployee searchEmployee(CustomQuerySearchEmployee implementation) {
        return implementation;
    }
}
