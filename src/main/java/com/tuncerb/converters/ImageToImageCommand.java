package com.tuncerb.converters;

import com.tuncerb.commands.ImageCommand;
import com.tuncerb.domain.Image;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by tuncer on 26/05/2018.
 */
@Component
public class ImageToImageCommand implements Converter<Image, ImageCommand> {

    @Synchronized
    @Nullable
    @Override
    public ImageCommand convert(Image image) {
        if (image == null) {
            return null;
        }

        ImageCommand imageCommand = new ImageCommand();
        imageCommand.setId(image.getId());
        if (image.getProduct() != null) {
            imageCommand.setProductId(image.getProduct().getId());
        }
        imageCommand.setUrl(image.getUrl());

        return imageCommand;
    }
}
