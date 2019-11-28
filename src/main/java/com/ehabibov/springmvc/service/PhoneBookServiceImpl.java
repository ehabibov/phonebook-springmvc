package com.ehabibov.springmvc.service;

import com.ehabibov.springmvc.entity.memory.Customer;
import com.ehabibov.springmvc.exception.EntityAlreadyExistException;
import com.ehabibov.springmvc.exception.ResourceNotFoundException;
import com.ehabibov.springmvc.repository.PhoneBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PhoneBookServiceImpl implements PhoneBookService {

    @Autowired
    private PhoneBookRepository repository;

    public PhoneBookServiceImpl() { }

    @Autowired
    public PhoneBookServiceImpl(PhoneBookRepository repository) {
        this.repository = repository;
    }

    public void setRepository(PhoneBookRepository repository) {
        this.repository = repository;
    }

    public Set<Customer> getAllContacts() {
        return repository.getAllContacts();
    }

    public Set<String> getAllPhonesByName(String name){
        return repository.getAllPhonesByName(name);
    }

    public void addContact(String name, String phone)  throws EntityAlreadyExistException {
        repository.addPhone(name, phone);
    }

    public void updatePhone(String name, String phone) throws ResourceNotFoundException {
        repository.updatePhone(name, phone);
    }

    public void removeContact(String name) throws ResourceNotFoundException {
        repository.removeContact(name);
    }

    public void reset(){
        repository.reset();
    }
}
