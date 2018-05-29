package com.tuncerb.converters;

import com.tuncerb.commands.CategoryCommand;
import com.tuncerb.commands.ProductCommand;
import com.tuncerb.domain.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by tuncer on 29/05/2018.
 */
public class ProductCommandToProductTest {
    public static final Long ID_VALUE = 1L;
    public static final String NAME = "productName";
    public static final String CATEGORY_NAME = "Category Name";
    ProductCommandToProduct conveter;

    @Before
    public void setUp() throws Exception {
        conveter = new ProductCommandToProduct(new CategoryCommandToCategory());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(conveter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(conveter.convert(new ProductCommand()));
    }

    @Test
    public void convert() throws Exception {
        CategoryCommand categoryCommand =new CategoryCommand();
        categoryCommand.setName(CATEGORY_NAME);

        ProductCommand productCommand = new ProductCommand();
        productCommand.setId(ID_VALUE);
        productCommand.setName(NAME);
        productCommand.setCategory(categoryCommand);

        Product product = conveter.convert(productCommand);

        assertEquals(ID_VALUE, product.getId());
        assertEquals(NAME, product.getName());
        assertEquals(CATEGORY_NAME, product.getCategory().getName());
    }
}
