package com.example.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class ProductModel {

    private String id;
    private String name;
    private int price;

    public ProductModel() {

    }

    public ProductModel(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}