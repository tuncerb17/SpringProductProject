package com.tuncerb.services;

import com.tuncerb.domain.Category;
import com.tuncerb.exceptions.NotFoundException;
import com.tuncerb.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by tuncer on 29/05/2018.
 */
public class CategoryServiceImpTest {

    CategoryServiceImp categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryServiceImp(categoryRepository);
    }

    @Test
    public void getCategoriesTest() throws Exception {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());

        Category category = new Category();
        category.setId(1L);

        categories.add(category);


        when(categoryService.getCategories()).thenReturn(categories);

        List<Category> categoryList = categoryService.getCategories();

        assertEquals(categoryList.size(), 2);
        verify(categoryRepository, times(1)).findAllByOrderByIdAsc();
        verify(categoryRepository, never()).findById(anyLong());
    }

    @Test
    public void getCategoryByIdTest() throws Exception {
        Category category = new Category();
        category.setId(1L);
        Optional<Category> categoryOptional = Optional.of(category);

        when(categoryRepository.findById(anyLong())).thenReturn(categoryOptional);

        Category categoryReturned = categoryService.findById(1L);

        assertNotNull("Null category returned", categoryReturned);
        verify(categoryRepository, times(1)).findById(anyLong());
        verify(categoryRepository, never()).findAll();
    }

    @Test(expected = NotFoundException.class)
    public void getCategoryByIdTestNotFound() throws Exception {

        Optional<Category> categoryOptional = Optional.empty();

        when(categoryRepository.findById(anyLong())).thenReturn(categoryOptional);

        categoryService.findById(1L);
    }
}
