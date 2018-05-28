package com.tuncerb.controllers;

import com.tuncerb.exceptions.ControllerExceptionHandler;
import com.tuncerb.services.CategoryService;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
}
