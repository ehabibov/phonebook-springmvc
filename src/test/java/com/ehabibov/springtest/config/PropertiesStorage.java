package com.ehabibov.springtest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:test.properties")
public class PropertiesStorage {

    @Value("${aut.host}:${aut.port}${batch.customers.dataload.endpoint}")
    public String customersJobUrl;
    @Value("${aut.host}:${aut.port}${customers.endpoint}")
    public String customersUrl;
    @Value("${aut.host}:${aut.port}${customers.reset.endpoint}")
    public String customersResetUrl;

    public String getCustomersJobUrl() {
        return customersJobUrl;
    }


    public String getCustomersUrl() {
        return customersUrl;
    }

    public String getCustomersResetUrl() {
        return customersResetUrl;
    }
}
