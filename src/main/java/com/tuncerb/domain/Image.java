package com.tuncerb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by tuncer on 26/05/2018.
 */
@Getter
@Setter
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne
    private Product product;
}
