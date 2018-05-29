package com.tuncerb.converters;

import com.tuncerb.commands.ProductCommand;
import com.tuncerb.domain.Category;
import com.tuncerb.domain.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by tuncer on 29/05/2018.
 */
public class ProductToProductCommandTest {
    public static final Long ID_VALUE = 1L;
    public static final String NAME = "productName";
    public static final String CATEGORY_NAME = "Product Name";
    ProductToProductCommand convter;

    CategoryToCategoryCommand categoryToCategoryCommand;
    @Before
    public void setUp() throws Exception {
        convter = new ProductToProductCommand(categoryToCategoryCommand);
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(convter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(convter.convert(new Product()));
    }

    @Test
    public void convert() throws Exception {
        Category category = new Category();
        category.setName(CATEGORY_NAME);

        Product product = new Product();
        product.setId(ID_VALUE);
        product.setName(NAME);
        product.setCategory(category);

        ProductCommand productCommand = convter.convert(product);

        assertEquals(ID_VALUE, productCommand.getId());
        assertEquals(NAME, productCommand.getName());
        assertEquals(CATEGORY_NAME, productCommand.getCategory().getName());

    }
}
