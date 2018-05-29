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
public class CategoryCommandToCategoryTest {
    public static final Long ID_VALUE = 1L;
    public static final String NAME = "categoryName";
    CategoryCommandToCategory conveter;

    @Before
    public void setUp() throws Exception {
        conveter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(conveter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(conveter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() throws Exception {
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setName(NAME);

        Category category = conveter.convert(categoryCommand);

        assertEquals(ID_VALUE, category.getId());
        assertEquals(NAME, category.getName());
    }
}
