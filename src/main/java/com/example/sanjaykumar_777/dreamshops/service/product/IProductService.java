package com.example.sanjaykumar_777.dreamshops.service.product;

import com.example.sanjaykumar_777.dreamshops.model.Product;

import java.util.List;

public interface IProductService {

    Product addProduct(Product product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    void updateProductById(Product product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByCategoryAndName(String category, String name);
    Long countProductsByBrandAndName(String brand, String name);

}
