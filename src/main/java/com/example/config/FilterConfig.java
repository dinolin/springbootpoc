package com.example.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.filter.LogApiFilter;
import com.example.filter.LogProcessTimeFilter;

@Configuration
public class FilterConfig {
	
    @Bean
    public FilterRegistrationBean logProcessTimeFilter() {
        FilterRegistrationBean<LogProcessTimeFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new LogProcessTimeFilter());
        bean.addUrlPatterns("/*");
        bean.setName("logProcessTimeFilter");
        bean.setOrder(1);

        return bean;
    }
    
    @Bean
    public FilterRegistrationBean logApiFilter() {
        FilterRegistrationBean<LogApiFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new LogApiFilter());
        bean.addUrlPatterns("/*");
        bean.setName("logApiFilter");
        bean.setOrder(0);

        return bean;
    }
}
