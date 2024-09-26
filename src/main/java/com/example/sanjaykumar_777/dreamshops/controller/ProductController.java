package com.example.sanjaykumar_777.dreamshops.controller;

import com.example.sanjaykumar_777.dreamshops.exception.ResourceNotFoundException;
import com.example.sanjaykumar_777.dreamshops.model.Product;
import com.example.sanjaykumar_777.dreamshops.request.AddProductRequest;
import com.example.sanjaykumar_777.dreamshops.request.ProductUpdateRequest;
import com.example.sanjaykumar_777.dreamshops.response.ApiResponse;
import com.example.sanjaykumar_777.dreamshops.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final IProductService productService;

    @GetMapping("/products")
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(new ApiResponse("success", null));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/product/{productId}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        try {
            Product theProduct = productService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
        try {
            Product theProduct = productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Add product success!", null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/product/{productId}/update")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequest product) {
        try {
            Product theProduct = productService.updateProduct(product, productId);
            return ResponseEntity.ok(new ApiResponse("Product update success!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Error", null));
        }
    }

    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId){
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Delete product success!",null));
        }
        catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    //TODO
    public ResponseEntity<ApiResponse> getProductsByBrandAndName(@PathVariable String brandName, @PathVariable String productName){
        final List<Product> products = productService.getProductsByBrandAndName(brandName, productName);
        return ResponseEntity.ok(new ApiResponse("success",products ));

    }

}
