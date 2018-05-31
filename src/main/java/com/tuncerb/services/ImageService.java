package com.tuncerb.services;

import org.springframework.web.multipart.MultipartFile;


/**
 * Created by tuncer on 26/05/2018.
 */
public interface ImageService {
    void init();
    void deleteAll();
    void saveImageFile(Long productId, MultipartFile[] files);
    void deleteById(Long productId, Long imageId);
}
