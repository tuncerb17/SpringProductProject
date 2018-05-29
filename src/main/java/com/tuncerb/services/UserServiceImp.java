package com.tuncerb.services;

import com.tuncerb.commands.UserCommand;
import com.tuncerb.converters.UserCommandToUser;
import com.tuncerb.converters.UserToUserCommand;
import com.tuncerb.domain.User;
import com.tuncerb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by tuncer on 27/05/2018.
 */
@Service
public class UserServiceImp implements UserService {
    private UserRepository userRepository;
    private UserCommandToUser userCommandToUser;
    private UserToUserCommand userToUserCommand;

    @Autowired
    public UserServiceImp(UserRepository userRepository, UserCommandToUser userCommandToUser, UserToUserCommand userToUserCommand) {
        this.userRepository = userRepository;
        this.userCommandToUser = userCommandToUser;
        this.userToUserCommand = userToUserCommand;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserCommand saveUserCommand(UserCommand command) {
        User detachedUser = userCommandToUser.convert(command);

        User savedUser = userRepository.save(detachedUser);

        return userToUserCommand.convert(savedUser);
    }

    @Override
    public boolean isUserNameValid(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        return !userOptional.isPresent();
    }
}
