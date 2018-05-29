package com.tuncerb.services;

import com.tuncerb.commands.ProductCommand;
import com.tuncerb.converters.ProductCommandToProduct;
import com.tuncerb.converters.ProductToProductCommand;
import com.tuncerb.domain.Product;
import com.tuncerb.exceptions.NotFoundException;
import com.tuncerb.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by tuncer on 29/05/2018.
 */
public class ProductServiceImpTest {

    ProductServiceImp productService;

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductCommandToProduct productCommandToProduct;

    @Mock
    ProductToProductCommand productToProductCommand;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        productService = new ProductServiceImp(productRepository, productCommandToProduct, productToProductCommand);
    }

    @Test
    public void getProductsTest() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(new Product());

        Product product = new Product();
        product.setId(1L);

        products.add(product);

        when(productService.getProducts()).thenReturn(products);

        List<Product> productList = productService.getProducts();

        assertEquals(productList.size(), 2);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void getProductByIdTest() throws Exception {
        Product product = new Product();
        product.setId(1L);
        Optional<Product> productOptional = Optional.of(product);

        when(productRepository.findById(anyLong())).thenReturn(productOptional);

        Product productReturned = productService.findById(1L);

        assertNotNull("Null product returned", productReturned);
        verify(productRepository, times(1)).findById(anyLong());
        verify(productRepository, never()).findAll();
    }

    @Test
    public void getProductCommandByIdTest() throws Exception {
        Product product = new Product();
        product.setId(1L);
        Optional<Product> productOptional = Optional.of(product);

        when(productRepository.findById(anyLong())).thenReturn(productOptional);


        ProductCommand productCommand = new ProductCommand();
        productCommand.setId(1L);

        when(productService.findCommandById(anyLong())).thenReturn(productCommand);

        ProductCommand productReturned = productService.findCommandById(productCommand.getId());

        assertNotNull("Null product returned", productReturned);
        verify(productRepository, times(2)).findById(anyLong());
    }

    @Test(expected = NotFoundException.class)
    public void getProductByIdTestNotFound() throws Exception {

        Optional<Product> productOptional = Optional.empty();

        when(productRepository.findById(anyLong())).thenReturn(productOptional);

        productService.findById(1L);
    }
}
