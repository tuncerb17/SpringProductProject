package com.tuncerb.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * Created by tuncer on 25/05/2018.
 */
@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;
}
