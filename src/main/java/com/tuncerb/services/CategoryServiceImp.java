package com.tuncerb.services;

import com.tuncerb.domain.Category;
import com.tuncerb.exceptions.NotFoundException;
import com.tuncerb.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by tuncer on 24/05/2018.
 */
@Service
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (!categoryOptional.isPresent()) {
            throw new NotFoundException("Category Not Found. For ID value: " + id.toString());
        }

        return categoryOptional.get();
    }
}
