package com.ehabibov.springmvc.repository;

import com.ehabibov.springmvc.entity.memory.Customer;
import com.ehabibov.springmvc.exception.EntityAlreadyExistException;
import com.ehabibov.springmvc.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public class PhoneBookDatabaseRepository implements PhoneBookRepository {

    @Override
    @Transactional
    public Set<Customer> getAllContacts() {
        return null;
    }

    @Override
    @Transactional
    public Set<String> getAllPhonesByName(String name) {
        return null;
    }

    @Override
    @Transactional
    public void addPhone(String name, String phone) throws EntityAlreadyExistException {

    }

    @Override
    @Transactional
    public void updatePhone(String name, String phone) throws ResourceNotFoundException {

    }

    @Override
    @Transactional
    public void removeContact(String phone) throws ResourceNotFoundException {

    }

    @Override
    @Transactional
    public void reset() {

    }
}