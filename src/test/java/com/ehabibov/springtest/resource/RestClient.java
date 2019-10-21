package com.ehabibov.springtest.resource;

import com.ehabibov.springtest.config.PropertiesStorage;
import com.ehabibov.springtest.context.ApplicationContextSingleton;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public abstract class RestClient {

    static AbstractApplicationContext context = ApplicationContextSingleton.getContext();
    static PropertiesStorage storage = context.getBean(PropertiesStorage.class);

    public <T> ResponseEntity<T> getExchangeAsSingle(String API, HttpHeaders headers, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity request = new HttpEntity<>(headers);
        return restTemplate.exchange(API, HttpMethod.GET, request, responseType);
    }

    public <T> ResponseEntity<List<T>> getExchangeAsList(String API, HttpHeaders headers, ParameterizedTypeReference<List<T>> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity request = new HttpEntity<>(headers);
        return restTemplate.exchange(API, HttpMethod.GET, request, responseType);
    }

    public <T> ResponseEntity<T> postExchange(String API, HttpHeaders headers, T binding) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity request = new HttpEntity<>(binding, headers);
        Class<T> bindingClass = (Class<T>) binding.getClass();
        return restTemplate.exchange(API, HttpMethod.POST, request, bindingClass);
    }

    public <T> ResponseEntity<T> putExchange(String API, HttpHeaders headers, T binding) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity request = new HttpEntity<>(binding, headers);
        Class<T> bindingClass = (Class<T>) binding.getClass();
        return restTemplate.exchange(API, HttpMethod.PUT, request, bindingClass);
    }

    public <T> ResponseEntity<T> deleteExchange(String API, HttpHeaders headers, T binding) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity request = new HttpEntity<>(binding, headers);
        Class<T> bindingClass = (Class<T>) binding.getClass();
        return restTemplate.exchange(API, HttpMethod.DELETE, request, bindingClass);
    }
}
