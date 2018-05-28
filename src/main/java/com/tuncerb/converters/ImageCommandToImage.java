package com.tuncerb.converters;

import com.tuncerb.commands.ImageCommand;
import com.tuncerb.domain.Image;
import com.tuncerb.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by tuncer on 26/05/2018.
 */
@Component
public class ImageCommandToImage implements Converter<ImageCommand, Image> {

    @Nullable
    @Override
    public Image convert(ImageCommand source) {
        if (source == null) {
            return null;
        }

        final Image image = new Image();
        image.setId(source.getId());

        if(source.getProductId() != null){
            Product product = new Product();
            product.setId(source.getProductId());
            image.setProduct(product);
            product.addImage(image);
        }

        image.setUrl(source.getUrl());

        return image;
    }
}
