package com.tuncerb.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tuncer on 25/05/2018.
 */
@Getter
@Setter
@NoArgsConstructor
public class ProductCommand {
    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @Size(min = 3, max = 255)
    private String description;

    @URL
    private String originalUrl;

    @URL
    private String imageUrl;

    private CategoryCommand category;

    private MultipartFile[] files;
}
