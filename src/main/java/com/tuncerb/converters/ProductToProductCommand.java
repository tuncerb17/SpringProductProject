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
    private final ImageToImageCommand imageConverter;

    public ProductToProductCommand(CategoryToCategoryCommand categoryConverter, ImageToImageCommand imageConverter) {
        this.categoryConverter = categoryConverter;
        this.imageConverter = imageConverter;
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
        productCommand.setCategory(categoryConverter.convert(product.getCategory()));

//        if (product.getImages() != null && product.getImages().size() > 0){
//            product.getImages()
//                    .forEach(image -> productCommand.getImages().add(imageConverter.convert(image)));
//        }

        return productCommand;
    }
}
