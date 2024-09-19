package com.example.sanjaykumar_777.dreamshops.repository;

import com.example.sanjaykumar_777.dreamshops.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
