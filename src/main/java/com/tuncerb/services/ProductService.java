package com.tuncerb.services;

import com.tuncerb.commands.ProductCommand;
import com.tuncerb.domain.Product;
import org.springframework.data.domain.Page;

import java.util.List;


/**
 * Created by tuncer on 24/05/2018.
 */
public interface ProductService {
    Product findById(Long id);
    List<Product> getProducts();
    ProductCommand findCommandById(Long id);
    ProductCommand saveProductCommand(ProductCommand command);
    Page<Product> paginatedProducts(int pageNumber, int pageSize);
    void deleteById(Long id);
}
