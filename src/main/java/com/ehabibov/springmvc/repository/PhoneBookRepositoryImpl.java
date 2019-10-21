package com.ehabibov.springmvc.repository;

import com.ehabibov.springmvc.entity.Customer;
import com.ehabibov.springmvc.exception.EntityAlreadyExistException;
import com.ehabibov.springmvc.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PhoneBookRepositoryImpl implements PhoneBookRepository {

    private Set<Customer> data;

    public PhoneBookRepositoryImpl() {
        this(new LinkedHashSet<>());
    }

    @Autowired
    public PhoneBookRepositoryImpl(Set<Customer> data) {
        this.data = new LinkedHashSet<>(data);
    }

    @Override
    public Set<Customer> getAllContacts() {
        return new LinkedHashSet<>(this.data);
    }

    @Override
    public Set<String> getAllPhonesByName(String name) {
        for (Customer customer : this.data){
            if (name.equals(customer.getName())){
                return customer.getPhoneNumbers();
            }
        }
        return null;
    }

    @Override
    public void addPhone(String name, String phone) throws EntityAlreadyExistException {
        Customer customer = this.getByPhone(phone);
        if (customer != null){
            throw new EntityAlreadyExistException("Phone number already assigned to: [" + customer.getName() +"]");
        }

        if (this.contactExists(name)){
            this.getByName(name).addPhoneNumber(phone);
        } else {
            data.add(new Customer() {{setName(name); addPhoneNumber(phone); }});
        }
    }

    @Override
    public void updatePhone(String name, String phone) throws ResourceNotFoundException {
        Customer byName = this.getByName(name);
        Customer byPhone = this.getByPhone(phone);
        if (byName == null){
            throw new ResourceNotFoundException("No contact with such name: [" + name + "]");
        } else if (byPhone != null && !byPhone.getName().equals(name)) {
            throw new ResourceNotFoundException("Phone number already assigned to: [" + byName.getName() + "]");
        } else {
            this.getByName(name).addPhoneNumber(phone);
        }
    }

    @Override
    public void removeContact(String name) throws ResourceNotFoundException {
        if (this.contactExists(name)){
            this.data.remove(this.getByName(name));
        } else {
            throw new ResourceNotFoundException("No contact with such name: [" + name + "]");
        }
    }

    @Override
    public void reset(){
        this.data.clear();
    }

    private boolean contactExists(String name) {
        for (Customer customer : this.data) {
            if (customer.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    private Customer getByName(String name){
        for (Customer customer : this.data){
            if (customer.getName().equals(name)){
                return customer;
            }
        }
        return null;
    }

    private Customer getByPhone(String phone){
        for (Customer customer : this.data){
            if (customer.getPhoneNumbers().contains(phone)){
                return customer;
            }
        }
        return null;
    }

    public void addObject(Customer customer){
        this.data.add(customer);
    }
}