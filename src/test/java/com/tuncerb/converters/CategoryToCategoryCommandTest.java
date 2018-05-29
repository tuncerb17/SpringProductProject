package com.tuncerb.converters;

import com.tuncerb.commands.CategoryCommand;
import com.tuncerb.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by tuncer on 29/05/2018.
 */
public class CategoryToCategoryCommandTest {
    public static final Long ID_VALUE = 1L;
    public static final String NAME = "categoryName";
    CategoryToCategoryCommand convter;

    @Before
    public void setUp() throws Exception {
        convter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(convter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(convter.convert(new Category()));
    }

    @Test
    public void convert() throws Exception {
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setName(NAME);

        CategoryCommand categoryCommand = convter.convert(category);

        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(NAME, categoryCommand.getName());

    }
}
