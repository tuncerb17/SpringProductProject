package com.tuncerb.services;

import com.tuncerb.domain.Category;
import java.util.List;

/**
 * Created by tuncer on 24/05/2018.
 */
public interface CategoryService {
    Category findById(Long l);
    List<Category> getCategories();
}
