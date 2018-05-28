package com.tuncerb.services;

import com.tuncerb.commands.ProductCommand;
import com.tuncerb.converters.ProductCommandToProduct;
import com.tuncerb.converters.ProductToProductCommand;
import com.tuncerb.domain.Category;
import com.tuncerb.domain.Image;
import com.tuncerb.domain.Product;
import com.tuncerb.exceptions.NotFoundException;
import com.tuncerb.repositories.ProductRepository;
import com.tuncerb.wrappers.PageWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by tuncer on 24/05/2018.
 */
@Service
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;
    private final ProductCommandToProduct productCommandToProduct;
    private final ProductToProductCommand productToProductCommand;

    public ProductServiceImp(ProductRepository productRepository, ProductCommandToProduct productCommandToProduct, ProductToProductCommand productToProductCommand) {
        this.productRepository = productRepository;
        this.productCommandToProduct = productCommandToProduct;
        this.productToProductCommand = productToProductCommand;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);


        if (!productOptional.isPresent()) {
            throw new NotFoundException("Product Not Found. For ID value: " + id.toString() );
        }

        return productOptional.get();
    }

    @Override
    public ProductCommand findCommandById(Long id) {
        return productToProductCommand.convert(findById(id));
    }

    @Override
    @Transactional
    public ProductCommand saveProductCommand(ProductCommand command) {
        Product detachedProduct = productCommandToProduct.convert(command);

        Product savedProduct = productRepository.save(detachedProduct);

        return productToProductCommand.convert(savedProduct);
    }

    public Page<Product> paginatedProducts(int pageNumber, int pageSize) {
        if(pageNumber < 1){
            pageNumber = 1;
        }
        PageRequest productPageReq = PageRequest.of(pageNumber -1, pageSize);

        return productRepository.findAllByOrderByIdDesc(productPageReq);
    }
}
