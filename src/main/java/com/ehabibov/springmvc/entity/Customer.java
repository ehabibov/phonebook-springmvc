package com.ehabibov.springmvc.entity;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Customer {

	private String name;
	private Set<String> phoneNumbers;
	
	public Customer() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void addPhoneNumber(String phoneNumber){
	    if (this.phoneNumbers == null){
	        this.phoneNumbers = new LinkedHashSet<>();
	        this.phoneNumbers.add(phoneNumber);
        } else {
            this.phoneNumbers.add(phoneNumber);
        }

    }

    @Override
    public String toString() {
        return String.format("Customer[name='%s', phoneNumbers=[%s]]", name, phoneNumbers.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return name.equals(customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
