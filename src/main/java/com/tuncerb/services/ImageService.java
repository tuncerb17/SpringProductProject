package com.tuncerb.services;

import com.tuncerb.domain.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by tuncer on 26/05/2018.
 */
public interface ImageService {
    void init();
    void deleteAll();
    void saveImageFile(Long productId, MultipartFile[] files);
    void deleteById(Long productId, Long imageId);
}
