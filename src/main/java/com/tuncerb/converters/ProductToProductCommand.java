package com.tuncerb.converters;

import com.tuncerb.commands.ProductCommand;
import com.tuncerb.domain.Product;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by tuncer on 25/05/2018.
 */
@Component
public class ProductToProductCommand implements Converter<Product, ProductCommand> {

    private final CategoryToCategoryCommand categoryConverter;

    public ProductToProductCommand(CategoryToCategoryCommand categoryConverter) {
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public ProductCommand convert(Product product) {
        if(product == null){
            return null;
        }

        final ProductCommand productCommand = new ProductCommand();
        productCommand.setId(product.getId());
        productCommand.setName(product.getName());
        productCommand.setDescription(product.getDescription());
        productCommand.setOriginalUrl(product.getOriginalUrl());
        productCommand.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null){
            productCommand.setCategory(categoryConverter.convert(product.getCategory()));
        }


        return productCommand;
    }
}
