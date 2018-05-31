package com.tuncerb.controllers;

import com.tuncerb.commands.ProductCommand;
import com.tuncerb.domain.Image;
import com.tuncerb.domain.Product;
import com.tuncerb.exceptions.ControllerExceptionHandler;
import com.tuncerb.exceptions.ContentNotFoundException;
import com.tuncerb.repositories.ProductRepository;
import com.tuncerb.services.CategoryService;
import com.tuncerb.services.ImageService;
import com.tuncerb.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by tuncer on 26/05/2018.
 */
public class ProductControllerTest {
    @Mock
    ProductService productService;
    @Mock
    CategoryService categoryService;
    @Mock
    ImageService imageService;

    @Mock
    ProductRepository productRepository;

    ProductController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new ProductController(productService, categoryService, imageService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testGetProduct() throws Exception {

        Product product = new Product();
        product.setId(1L);

        when(productService.findById(anyLong())).thenReturn(product);

        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/show"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    public void testGetProductNotFound() throws Exception {
        when(productService.findById(anyLong())).thenThrow(ContentNotFoundException.class);
        mockMvc.perform(get("/product/1"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

    @Test
    public void testGetProductNumberFormatException() throws Exception {
        mockMvc.perform(get("/product/notFound"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

    @Test
    public void testGetNewProductForm() throws Exception {
        mockMvc.perform(get("/product/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/form"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    public void testPostNewProductForm() throws Exception {
        ProductCommand command = new ProductCommand();
        command.setId(2L);

        when(productService.saveProductCommand(any())).thenReturn(command);

        MockMultipartFile multipartFile = new MockMultipartFile("files", "test.txt",
                "text/plain", "Image Byte".getBytes());

        mockMvc.perform(
                multipart("/product").file(multipartFile)
                        .param("id", "")
                        .param("name", "Product Name")
                        .param("description", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }

    @Test
    public void testPostNewProductFormValidationFail() throws Exception {
        ProductCommand command = new ProductCommand();
        command.setId(2L);

        when(productService.saveProductCommand(any())).thenReturn(command);

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("cookTime", "3000")

        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("product"))
                .andExpect(view().name("product/form"));
    }

    @Test
    public void testGetUpdateView() throws Exception {
        ProductCommand command = new ProductCommand();
        command.setId(2L);

        when(productService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/product/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/form"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    public void testDeleteProductImage() throws Exception {
        Product product = new Product();
        product.setId(1L);

        Image image = new Image();
        image.setId(1L);
        image.setProduct(product);

        mockMvc.perform(delete("/product/" + product.getId() + "/image/" + image.getId()))
                .andExpect(status().isOk());

        verify(imageService, times(1)).deleteById(anyLong(), anyLong());
    }

    @Test
    public void testDeleteAction() throws Exception {

        mockMvc.perform(delete("/product/1")).andExpect(status().isOk());

        verify(productService, times(1)).deleteById(anyLong());
    }

    @Test
    public void testDeleteProductByIdTestNotFound() throws Exception {
        Long idToDelete = 2L;
        when(productService.findById(anyLong())).thenThrow(ContentNotFoundException.class);
        productService.deleteById(idToDelete);
    }
}
