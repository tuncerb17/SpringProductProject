package com.tuncerb.services;

import com.tuncerb.commands.ProductCommand;
import com.tuncerb.converters.ProductCommandToProduct;
import com.tuncerb.converters.ProductToProductCommand;
import com.tuncerb.domain.Product;
import com.tuncerb.repositories.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * Created by tuncer on 29/05/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceIT {
    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCommandToProduct productCommandToProduct;

    @Autowired
    ProductToProductCommand productToProductCommand;

    @Transactional
    @Test
    public void testSaveProductCommand() throws Exception {
        //given
        Iterable<Product> products = productRepository.findAll();
        Product testProduct = products.iterator().next();
        ProductCommand testProductCommand = productToProductCommand.convert(testProduct);

        //when
        testProductCommand.setDescription(NEW_DESCRIPTION);
        ProductCommand savedProductCommand = productService.saveProductCommand(testProductCommand);

        //then
        assertEquals(NEW_DESCRIPTION, savedProductCommand.getDescription());
        assertEquals(testProduct.getId(), savedProductCommand.getId());
        assertEquals(testProduct.getCategory().getId(), savedProductCommand.getCategory().getId());
    }
}
