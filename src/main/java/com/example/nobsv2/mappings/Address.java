package com.example.nobsv2.mappings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @ManyToMany(mappedBy = "addresses")
    //Ignore this when constructing JSON to avoid circular reference (customer points to address that points to customer and so on)
    @JsonIgnore
    private List<Customer> customers;

    //Getters
    public Integer getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
