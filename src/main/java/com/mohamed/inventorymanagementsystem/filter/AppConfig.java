package com.mohamed.inventorymanagementsystem.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public FilterRegistrationBean<LoggingFilter>filterRegistrationBean(){
        FilterRegistrationBean<LoggingFilter>registrationBean=new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoggingFilter());
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setSales(1);
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean<CustomFilter>myFilter(){
        FilterRegistrationBean<CustomFilter>registrationBean=new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomFilter());
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setSales(2);
        return registrationBean;
    }
}
