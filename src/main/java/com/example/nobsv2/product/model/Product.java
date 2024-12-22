package com.example.nobsv2.product.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto generates the id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    //@JsonProperty("name")
    private String name;

    @Column(name = "description")
    //@JsonProperty("description")
    private String description;

    @Column(name = "price")
    //@JsonProperty("price")
    private Double price;


    //Getters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
