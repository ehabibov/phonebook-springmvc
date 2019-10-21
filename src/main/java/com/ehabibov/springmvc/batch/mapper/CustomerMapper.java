package com.ehabibov.springmvc.batch.mapper;

import com.ehabibov.springmvc.entity.Customer;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CustomerMapper implements FieldSetMapper<Customer> {

    @Override
    public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
        Customer customer = new Customer();
        Set<String> phones = new HashSet<>();
        customer.setName(fieldSet.readString("name"));
        Collections.addAll(phones, fieldSet.readString("phoneNumbers").split(","));
        customer.setPhoneNumbers(phones);
        return customer;
    }
}