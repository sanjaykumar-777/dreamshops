package com.example.sanjaykumar_777.dreamshops.service.image;

import com.example.sanjaykumar_777.dreamshops.dto.ImageDto;
import com.example.sanjaykumar_777.dreamshops.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImages(Long productId, List<MultipartFile> files);
    void updateImage(MultipartFile file, Long imageId);
}
