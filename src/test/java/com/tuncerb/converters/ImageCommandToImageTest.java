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
public class ImageCommandToImageTest {

    public static final Long ID_VALUE = 1L;
    public static final String URL = "https://docs.spring.io/";
    ImageCommandToImage conveter;

    @Before
    public void setUp() throws Exception {
        conveter = new ImageCommandToImage();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(conveter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(conveter.convert(new ImageCommand()));
    }

    @Test
    public void convert() throws Exception {
        Product product = new Product();
        product.setId(ID_VALUE);

        ImageCommand imageCommand = new ImageCommand();
        imageCommand.setId(ID_VALUE);
        imageCommand.setUrl(URL);
        imageCommand.setProductId(product.getId());

        Image image = conveter.convert(imageCommand);

        assertEquals(ID_VALUE, image.getId());
        assertEquals(URL, image.getUrl());
        assertEquals(ID_VALUE, image.getProduct().getId());
    }
}
