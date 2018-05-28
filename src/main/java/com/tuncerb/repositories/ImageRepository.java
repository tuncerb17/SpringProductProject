package com.tuncerb.repositories;

import com.tuncerb.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tuncer on 24/05/2018.
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
}

