package com.tuncerb.services;

import com.tuncerb.commands.UserCommand;
import com.tuncerb.domain.User;

import java.util.Optional;

/**
 * Created by tuncer on 27/05/2018.
 */
public interface UserService {
    Optional<User> findByUsername(String username);
    UserCommand saveUserCommand(UserCommand command);
}
