package com.example.sanjaykumar_777.dreamshops.model;

import jakarta.persistence.OneToMany;

import java.util.List;

public class Category {
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
