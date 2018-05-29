package com.tuncerb.controllers;

import com.tuncerb.domain.Category;
import com.tuncerb.domain.Product;
import com.tuncerb.exceptions.ControllerExceptionHandler;
import com.tuncerb.repositories.ProductRepository;
import com.tuncerb.services.CategoryService;
import com.tuncerb.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by tuncer on 29/05/2018.
 */
public class IndexControllerTest {
    @Mock
    CategoryService categoryService;
    @Mock
    ProductService productService;
    @Mock
    ProductRepository productRepository;

    IndexController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new IndexController(categoryService, productService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testGetHomePage() throws Exception {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());

        Category category = new Category();
        category.setId(1L);

        categories.add(category);

        when(categoryService.getCategories()).thenReturn(categories);
        when(productService.paginatedProducts(anyInt(), anyInt())).thenReturn(Page.<Product>empty());

        mockMvc.perform(get("/?page=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attributeExists("categories"));
    }
}
