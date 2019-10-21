package com.ehabibov.springtest.bindings;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class UpdateCustomerRequestBinding implements Serializable {
    private static final long serialVersionUID = -325375856745022739L;

    @JsonProperty("phoneNumber")
    private String phone;

    private UpdateCustomerRequestBinding(Builder builder){
        this.phone = builder.phone;
    }

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

    public static class Builder {

        private String phone;

        public Builder setPhone(String phone){
            this.phone = phone;
            return this;
        }

        public UpdateCustomerRequestBinding build(){
            return new UpdateCustomerRequestBinding(this);
        }
    }
}