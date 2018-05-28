package com.tuncerb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by tuncer on 27/05/2018.
 */
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password")
    private String password;
}
