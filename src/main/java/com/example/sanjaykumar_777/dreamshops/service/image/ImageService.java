package com.example.sanjaykumar_777.dreamshops.service.image;

import com.example.sanjaykumar_777.dreamshops.dto.ImageDto;
import com.example.sanjaykumar_777.dreamshops.exception.ResourceNotFoundException;
import com.example.sanjaykumar_777.dreamshops.model.Image;
import com.example.sanjaykumar_777.dreamshops.model.Product;
import com.example.sanjaykumar_777.dreamshops.repository.ImageRepository;
import com.example.sanjaykumar_777.dreamshops.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;
    private final IProductService iProductService;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No image found with id: "+id));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete ,() -> {
            throw new ResourceNotFoundException("No image found with id: "+id);
        });
    }

    @Override
    public List<ImageDto> saveImages(Long productId, List<MultipartFile> files) {

        Product product = iProductService.getProductById(productId);
        List<ImageDto> savedImageDto = new ArrayList<>();

        for(MultipartFile file : files){
            try {
                Image image = new Image();
                image.setFileName(file.getName());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String buildDownloadUrl = "api/v1/images/image/download/";
                String downloadUrl= buildDownloadUrl+image.getId(); //at this point image id will be null
                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepository.save(image);

                savedImage.setDownloadUrl(buildDownloadUrl+savedImage.getId());
                imageRepository.save(savedImage);

                ImageDto imageDto = new ImageDto();
                imageDto.setId(savedImage.getId());
                image.setFileName(savedImage.getFileName());
                imageDto.setDownloadUrl(savedImage.getDownloadUrl());
                savedImageDto.add(imageDto);
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedImageDto;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        try {
            Image image = getImageById(imageId);
            image.setFileType(file.getContentType());
            image.setFileName(file.getName());
            image.setImage(new SerialBlob(file.getBytes()));
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
