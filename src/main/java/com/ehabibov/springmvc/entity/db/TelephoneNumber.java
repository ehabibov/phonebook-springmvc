package com.ehabibov.springmvc.entity.db;

import javax.persistence.*;

@Entity
@Table(name="telephone_numbers")
public class TelephoneNumber {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "number")
    private String telephoneNumber;

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public String toString() {
        return String.format("TelephoneNumber(telephoneNumber=%s)", this.telephoneNumber);
    }
}
