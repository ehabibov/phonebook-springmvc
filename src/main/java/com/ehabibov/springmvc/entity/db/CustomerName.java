package com.ehabibov.springmvc.entity.db;

import javax.persistence.*;

@Entity
@Table(name="customers")
public class CustomerName {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("CustomerName(name=%s)", this.name);
    }
}
