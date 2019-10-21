package com.ehabibov.springmvc.controller;

import com.ehabibov.springmvc.bindings.CreateCustomerRequestBinding;
import com.ehabibov.springmvc.bindings.UpdateCustomerRequestBinding;
import com.ehabibov.springmvc.entity.Customer;
import com.ehabibov.springmvc.exception.EntityAlreadyExistException;
import com.ehabibov.springmvc.exception.ResourceNotFoundException;
import com.ehabibov.springmvc.service.PhoneBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/customers")
public class PhoneBookController {

    @Autowired
    private PhoneBookService phoneBookService;

    @GetMapping
    public Set<Customer> listCustomers() {
        return phoneBookService.getAllContacts();
    }

    @GetMapping("/{name}")
    public Set<String> getCustomer(@PathVariable String name) {
        return phoneBookService.getAllPhonesByName(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void newCustomer(@RequestBody CreateCustomerRequestBinding customer) throws EntityAlreadyExistException {
        phoneBookService.addContact(customer.getName(), customer.getPhone());
    }

    @PutMapping("/{name}")
    public void updateCustomer(@PathVariable String name, @RequestBody UpdateCustomerRequestBinding customer)
            throws ResourceNotFoundException {
        phoneBookService.updatePhone(name, customer.getPhone());
    }

    @DeleteMapping("/{name}")
    public void deleteCustomer(@PathVariable String name) throws ResourceNotFoundException {
        phoneBookService.removeContact(name);
    }

    @GetMapping("/reset")
    public void getCustomer() {
        phoneBookService.reset();
    }

}