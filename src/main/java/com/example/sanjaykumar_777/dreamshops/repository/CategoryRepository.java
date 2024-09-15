package com.example.sanjaykumar_777.dreamshops.repository;

import com.example.sanjaykumar_777.dreamshops.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);
}
