package com.tuncerb.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * Created by tuncer on 27/05/2018.
 */
@Getter
@Setter
@NoArgsConstructor
public class UserCommand {
    private Long id;

    @NotNull
    private String username;

    @Column(name = "password")
    @Transient
    private String password;
}
