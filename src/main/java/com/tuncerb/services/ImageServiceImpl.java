package com.tuncerb.services;

import com.github.slugify.Slugify;
import com.tuncerb.commands.ImageCommand;
import com.tuncerb.exceptions.StorageException;
import com.tuncerb.config.StorageProperties;
import com.tuncerb.converters.ImageCommandToImage;
import com.tuncerb.domain.Image;
import com.tuncerb.domain.Product;
import com.tuncerb.repositories.ImageRepository;
import com.tuncerb.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by tuncer on 24/05/2018.
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final ImageCommandToImage imageCommandToImage;
    private final Path rootLocation;


    @Autowired
    public ImageServiceImpl(ProductRepository productService, StorageProperties properties, ImageRepository imageRepository, ImageCommandToImage imageCommandToImage) {
        this.productRepository = productService;
        this.imageRepository = imageRepository;
        this.imageCommandToImage = imageCommandToImage;
        this.rootLocation = Paths.get(properties.getLocation());;
    }

    @Override
    @Transactional
    public void saveImageFile(Long productId, MultipartFile[] files) {
        Product product = productRepository.findById(productId).get();
            for(MultipartFile file : files) {
                String filename = StringUtils.cleanPath(file.getOriginalFilename());
                try {
                    if (file.isEmpty()) {
                        throw new StorageException("Failed to store empty file " + filename);
                    }
                    if (filename.contains("..")) {
                        // This is a security check
                        throw new StorageException(
                                "Cannot store file with relative path outside current directory "
                                        + filename);
                    }
                    String uuid = UUID.randomUUID().toString();
                    Slugify slg = new Slugify();
                    String slugifyFilename = slg.slugify(product.getName() +'-'+ uuid) +'.'+ filename.split("\\.")[1];

                    try (InputStream inputStream = file.getInputStream()) {
                        Files.copy(inputStream, this.rootLocation.resolve(slugifyFilename),
                                StandardCopyOption.REPLACE_EXISTING);
                    }
                    ImageCommand imageCommand = new ImageCommand();
                    imageCommand.setUrl(Paths.get("/images", slugifyFilename).toString());
                    imageCommand.setProductId(product.getId());
                    imageRepository.save(imageCommandToImage.convert(imageCommand));
                }
                catch (IOException e) {
                    throw new StorageException("Failed to store file " + filename, e);
                }
            }
    }

    @Override
    public void deleteById(Long productId, Long imageId) {
        Optional<Product> productOptional = productRepository.findById(productId);

        if(productOptional.isPresent()){
            Product product = productOptional.get();

            Optional<Image> imageOptional = product
                    .getImages()
                    .stream()
                    .filter(image -> image.getId().equals(imageId))
                    .findFirst();

            if(imageOptional.isPresent()){
                Image imageToDelete = imageOptional.get();
                imageToDelete.setProduct(null);
                product.getImages().remove(imageOptional.get());
                productRepository.save(product);
            }else {
                throw new RuntimeException("Image Not Found!");
            }
        } else {
            throw new RuntimeException("Product Not Found!");
        }

    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}