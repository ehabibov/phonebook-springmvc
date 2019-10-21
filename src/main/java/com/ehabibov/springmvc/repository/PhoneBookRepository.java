package com.ehabibov.springmvc.repository;

import com.ehabibov.springmvc.entity.Customer;
import com.ehabibov.springmvc.exception.EntityAlreadyExistException;
import com.ehabibov.springmvc.exception.ResourceNotFoundException;

import java.util.Set;

public interface PhoneBookRepository {

    Set<Customer> getAllContacts();

    Set<String> getAllPhonesByName(String name);

    void addPhone(String name, String phone) throws EntityAlreadyExistException;

    void updatePhone(String name, String phone) throws ResourceNotFoundException;

    void removeContact(String phone) throws ResourceNotFoundException;

    void reset();
}
