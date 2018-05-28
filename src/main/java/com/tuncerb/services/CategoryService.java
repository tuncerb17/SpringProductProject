package com.tuncerb.services;

import com.tuncerb.domain.Category;
import com.tuncerb.domain.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

/**
 * Created by tuncer on 24/05/2018.
 */
public interface CategoryService {
    Category findById(Long l);
    List<Category> getCategories();
}
