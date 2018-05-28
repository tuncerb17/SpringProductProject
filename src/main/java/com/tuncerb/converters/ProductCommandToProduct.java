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
    private final ImageCommandToImage imageConverter;

    public ProductCommandToProduct(CategoryCommandToCategory categoryConverter, ImageCommandToImage imageConverter) {
        this.categoryConverter = categoryConverter;
        this.imageConverter = imageConverter;
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
        product.setCategory(categoryConverter.convert(productCommand.getCategory()));

//        if (productCommand.getImages() != null && productCommand.getImages().size() > 0){
//            productCommand.getImages()
//                    .forEach(image -> product.getImages().add(imageConverter.convert(image)));
//        }

        return product;
    }
}
