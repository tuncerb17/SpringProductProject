package com.tuncerb.repositories;

import com.tuncerb.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tuncer on 24/05/2018.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByOrderByIdDesc(Pageable pageable);
    Page<Product> findAllByCategoryIdOrderByIdDesc(Long categoryId,Pageable pageable);
}