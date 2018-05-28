package com.tuncerb.converters;

import com.tuncerb.commands.CategoryCommand;
import com.tuncerb.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by tuncer on 25/05/2018.
 */
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if (categoryCommand == null){
            return null;
        }

        final Category category = new Category();
        category.setId(categoryCommand.getId());
        category.setName(categoryCommand.getName());

        return  category;
    }
}
