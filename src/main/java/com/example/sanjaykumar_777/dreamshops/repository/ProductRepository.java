package com.example.sanjaykumar_777.dreamshops.repository;

import com.example.sanjaykumar_777.dreamshops.model.Category;
import com.example.sanjaykumar_777.dreamshops.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategoryName(String category);
    List<Product> findByBrand(String brand);

//    List<Product> findByCategoryAndBrand(String category, String brand);

    List<Product> findByName(String name);

//    List<Product> findByCategoryAndName(String category, String name);

    Long countByBrandAndName(String brand, String name);
}
