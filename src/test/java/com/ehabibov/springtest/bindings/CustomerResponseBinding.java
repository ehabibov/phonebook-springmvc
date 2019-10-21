package com.ehabibov.springtest.bindings;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Set;

public class CustomerResponseBinding implements Serializable {

    private String name;
    @JsonProperty("phoneNumbers")
    private Set<String> phones;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return String.format("CustomerResponseBinding{name='%s', phones=%s}", name, phones);
    }
}
