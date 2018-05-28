package com.tuncerb.services;

import com.tuncerb.commands.ProductCommand;
import com.tuncerb.converters.CategoryToCategoryCommand;
import com.tuncerb.domain.Category;
import com.tuncerb.domain.Product;
import com.tuncerb.repositories.CategoryRepository;
import com.tuncerb.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by tuncer on 24/05/2018.
 */
@Service
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryToCategoryCommand categoryToCategoryCommand;
    private final ProductRepository productRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository, CategoryToCategoryCommand categoryToCategoryCommand, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.productRepository = productRepository;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (!categoryOptional.isPresent()) {
            throw new RuntimeException("Category Not Found!");
            //throw new NotFoundException("Category Not Found. For ID value: " + id.toString() );
        }

        return categoryOptional.get();
    }
}
