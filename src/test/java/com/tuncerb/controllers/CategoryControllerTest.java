package com.tuncerb.controllers;

import com.tuncerb.domain.Category;
import com.tuncerb.exceptions.ControllerExceptionHandler;
import com.tuncerb.exceptions.NotFoundException;
import com.tuncerb.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by tuncer on 26/05/2018.
 */
public class CategoryControllerTest {
    @Mock
    CategoryService categoryService;

    CategoryController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new CategoryController(categoryService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testGetCategoryProductsPage() throws Exception {
        Category category = new Category();
        category.setId(1L);

        when(categoryService.findById(anyLong())).thenReturn(category);

        mockMvc.perform(get("/category/1/product"))
                .andExpect(status().isOk())
                .andExpect(view().name("category/productlist"))
                .andExpect(model().attributeExists("category"))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    public void testGetUnknownCategoryProductsPage() throws Exception {
        when(categoryService.findById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/category/1/product"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

    @Test
    public void testGetCategoryNumberFormatException() throws Exception {
        mockMvc.perform(get("/category/notFound/product"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }
}
