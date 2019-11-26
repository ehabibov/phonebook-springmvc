package com.ehabibov.springtest.resource;

import com.ehabibov.springtest.bindings.CreateCustomerRequestBinding;
import com.ehabibov.springtest.bindings.CustomerResponseBinding;
import com.ehabibov.springtest.bindings.UpdateCustomerRequestBinding;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public static CustomerResponseBinding findCustomerWithName(List<CustomerResponseBinding> customers, String name){
        for(CustomerResponseBinding customer : customers){
            if (customer.getName().equals(name)){
                return customer;
            }
        }
        return null;
    }

    public ResponseEntity<Set> getCustomer(String name) {
        return this.getExchangeAsSingle(
                this.getCustomersUrl().concat("/").concat(name),
                this.getHeaders(),
                Set.class);
    }

    public ResponseEntity<List<CustomerResponseBinding>> getCustomers() {
        return this.getExchangeAsList(
                this.getCustomersUrl(),
                this.getHeaders(),
                new ParameterizedTypeReference<List<CustomerResponseBinding>>() {});
    }

    public ResponseEntity createCustomer(CreateCustomerRequestBinding contact) {
        return this.postExchange(
                this.getCustomersUrl(),
                this.getHeaders(),
                contact);
    }

    public ResponseEntity updateCustomer(String name, UpdateCustomerRequestBinding updateBinding) {
        return this.putExchange(
                this.getCustomersUrl().concat("/").concat(name),
                this.getHeaders(),
                updateBinding);
    }

    public ResponseEntity deleteCustomer(String name, Serializable binding) {
        return this.deleteExchange(
                this.getCustomersUrl().concat("/").concat(name),
                this.getHeaders(),
                binding);
    }
}
