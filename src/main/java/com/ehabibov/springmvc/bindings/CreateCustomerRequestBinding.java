package com.ehabibov.springmvc.bindings;

public class CreateCustomerRequestBinding implements java.io.Serializable {
    private static final long serialVersionUID = 2359710841348114969L;

    private String name;
    private String phone;

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
}
