package com.ehabibov.springmvc.service;

import com.ehabibov.springmvc.entity.Customer;
import com.ehabibov.springmvc.exception.EntityAlreadyExistException;
import com.ehabibov.springmvc.exception.ResourceNotFoundException;

import java.util.Set;

public interface PhoneBookService {

    Set<Customer> getAllContacts();

    Set<String> getAllPhonesByName(String name);

    void addContact(String name, String phone) throws EntityAlreadyExistException;

    void updatePhone(String name, String phone) throws ResourceNotFoundException;

    void removeContact(String name) throws ResourceNotFoundException ;

    void reset();

}