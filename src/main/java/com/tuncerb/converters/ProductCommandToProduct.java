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
public class ProductCommandToProduct implements Converter<ProductCommand, Product> {

    private final CategoryCommandToCategory categoryConverter;

    public ProductCommandToProduct(CategoryCommandToCategory categoryConverter) {
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Product convert(ProductCommand productCommand) {
        if (productCommand == null) {
            return null;
        }

        final Product product = new Product();
        product.setId(productCommand.getId());
        product.setName(productCommand.getName());
        product.setDescription(productCommand.getDescription());
        product.setOriginalUrl(productCommand.getOriginalUrl());
        product.setImageUrl(productCommand.getImageUrl());
        if(productCommand.getCategory() != null){
            product.setCategory(categoryConverter.convert(productCommand.getCategory()));
        }

        return product;
    }
}
