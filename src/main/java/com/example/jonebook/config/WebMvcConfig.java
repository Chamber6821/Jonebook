package com.example.jonebook.config;

import com.example.jonebook.RequestCounter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

  public final RequestCounter counter;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(counter).addPathPatterns("/api/**", "/api/**/*");
  }
}
