package com.example.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.auth.UserIdentity;
import com.example.repository.AppUserRepository;
import com.example.repository.ProductRepository;
import com.example.service.AppUserService;
import com.example.service.MailService;
import com.example.service.ProductService;

@Configuration
public class ServiceConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ProductService productService(ProductRepository repository,
                                         MailService mailService,
                                         UserIdentity userIdentity) {
        return new ProductService(repository, mailService, userIdentity);
    }
    
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AppUserService appuserService(AppUserRepository repository) {
        return new AppUserService(repository);
    }
}
