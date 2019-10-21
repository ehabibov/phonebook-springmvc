package com.ehabibov.springmvc.config;

import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.*;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.ehabibov.springmvc"})
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true)
                .useRegisteredExtensionsOnly(true)
                .favorParameter(false)
                .ignoreAcceptHeader(true)
                .defaultContentType(MediaType.ALL);
    }

    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);
        resolver.setDefaultViews(Arrays.asList(new MappingJackson2JsonView()));
        resolver.setOrder(1);
        return resolver;
    }

}