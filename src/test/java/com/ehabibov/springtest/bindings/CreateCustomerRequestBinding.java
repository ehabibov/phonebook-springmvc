package com.ehabibov.springtest.bindings;

import java.io.Serializable;

public class CreateCustomerRequestBinding implements Serializable {
    private static final long serialVersionUID = -5859835233536461401L;

    private String name;
    private String phone;

    private CreateCustomerRequestBinding(Builder builder){
        this.name = builder.name;
        this.phone = builder.phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("CustomerRequestBinding{name='%s, phone='%s'}'", name, phone);
    }

    public static class Builder {

        private String name;
        private String phone;

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setPhone(String phone){
            this.phone = phone;
            return this;
        }

        public CreateCustomerRequestBinding build(){
            return new CreateCustomerRequestBinding(this);
        }
    }
}
