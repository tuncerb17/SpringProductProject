package com.tuncerb.repositories;

import com.tuncerb.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tuncer on 24/05/2018.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByOrderByIdDesc(Pageable pageable);
}