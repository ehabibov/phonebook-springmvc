package com.ehabibov.springmvc.bindings;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class UpdateCustomerRequestBinding implements Serializable {
    private static final long serialVersionUID = -8877746578892993778L;

    @JsonProperty("phoneNumber")
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("UpdateCustomerRequestBinding{phone='%s'}", phone);
    }
}