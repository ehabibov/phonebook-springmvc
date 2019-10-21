package com.ehabibov.springtest.resource;

import com.ehabibov.springtest.bindings.CustomerResponseBinding;
import com.ehabibov.springtest.config.TestConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerResource extends RestClient {

    private HttpHeaders headers;

    public String getCustomersUrl() {
        return storage.getCustomersUrl();
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    @PostConstruct
    public void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(new ArrayList<MediaType>() {{
            add(MediaType.APPLICATION_JSON);
        }});
    }

    public CustomerResponseBinding getCustomerWithName(List<CustomerResponseBinding> customers, String name){
        for(CustomerResponseBinding customer : customers){
            if (customer.getName().equals(name)){
                return customer;
            }
        }
        return null;
    }
}
