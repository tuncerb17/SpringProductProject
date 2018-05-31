package com.tuncerb.services;

import com.tuncerb.commands.ProductCommand;
import com.tuncerb.constants.PaginationConstants;
import com.tuncerb.converters.ProductCommandToProduct;
import com.tuncerb.converters.ProductToProductCommand;
import com.tuncerb.domain.Product;
import com.tuncerb.exceptions.ContentNotFoundException;
import com.tuncerb.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by tuncer on 24/05/2018.
 */
@Service
public class ProductServiceImp implements ProductService {

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
            throw new ContentNotFoundException("Product Not Found. For ID value: " + id.toString());
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
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        int size = pageSize != 0 ? pageSize : PaginationConstants.ITEM_COUNT;
        PageRequest productPageReq = PageRequest.of(pageNumber - 1, size);

        return productRepository.findAllByOrderByIdDesc(productPageReq);
    }

    public Page<Product> paginatedCategoryProducts(Long categoryId, int pageNumber, int pageSize) {
        if (pageNumber < 1) {
            pageNumber = 1;
        }

        int size = pageSize != 0 ? pageSize : PaginationConstants.ITEM_COUNT;
        PageRequest productPageReq = PageRequest.of(pageNumber - 1, size);

        return productRepository.findAllByCategoryIdOrderByIdDesc(categoryId, productPageReq);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (!productOptional.isPresent()) {
            throw new ContentNotFoundException("Product Not Found. For ID value: " + String.valueOf(id));
        }

        productRepository.deleteById(id);
    }
}
