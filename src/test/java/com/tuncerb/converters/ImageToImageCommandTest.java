package com.tuncerb.converters;

import com.tuncerb.commands.ImageCommand;
import com.tuncerb.domain.Image;
import com.tuncerb.domain.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by tuncer on 29/05/2018.
 */
public class ImageToImageCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String URL = "https://docs.spring.io/";
    ImageToImageCommand convter;

    @Before
    public void setUp() throws Exception {
        convter = new ImageToImageCommand();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(convter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(convter.convert(new Image()));
    }

    @Test
    public void convert() throws Exception {
        Product product = new Product();
        product.setId(ID_VALUE);

        Image image = new Image();
        image.setId(ID_VALUE);
        image.setUrl(URL);
        image.setProduct(product);

        ImageCommand ImageCommand = convter.convert(image);

        assertEquals(ID_VALUE, ImageCommand.getId());
        assertEquals(URL, ImageCommand.getUrl());
        assertEquals(ID_VALUE, ImageCommand.getProductId());

    }
}
