package com.tuncerb.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by tuncer on 26/05/2018.
 */
@Getter
@Setter
@NoArgsConstructor
public class ImageCommand {
    private Long id;

    private String url;
    private Long productId;

}
