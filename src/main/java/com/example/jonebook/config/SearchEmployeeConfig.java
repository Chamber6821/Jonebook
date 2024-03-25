package com.example.jonebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.jonebook.services.search.CustomQuerySearchEmployee;
import com.example.jonebook.services.search.MemCachedSearchEmployee;
import com.example.jonebook.services.search.SearchEmployee;

@Configuration
public class SearchEmployeeConfig {
    @Primary
    @Bean
    public SearchEmployee searchEmployee(CustomQuerySearchEmployee implementation) {
        return new MemCachedSearchEmployee(implementation);
    }
}
