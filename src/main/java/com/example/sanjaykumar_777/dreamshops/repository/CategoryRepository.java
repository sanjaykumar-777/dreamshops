package com.example.sanjaykumar_777.dreamshops.repository;

import com.example.sanjaykumar_777.dreamshops.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);
    boolean existsByName(String name);
}
