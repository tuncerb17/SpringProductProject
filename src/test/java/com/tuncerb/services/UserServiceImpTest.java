package com.tuncerb.services;

import com.tuncerb.converters.UserCommandToUser;
import com.tuncerb.converters.UserToUserCommand;
import com.tuncerb.domain.User;
import com.tuncerb.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by tuncer on 29/05/2018.
 */
public class UserServiceImpTest {

    UserServiceImp userService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserCommandToUser userCommandToUser;

    @Mock
    UserToUserCommand userToUserCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userService = new UserServiceImp(userRepository,userCommandToUser,userToUserCommand);
    }

    @Test
    public void getUserByUsernameTest() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("userName");
        Optional<User> userOptional = Optional.of(user);

        when(userRepository.findById(anyLong())).thenReturn(userOptional);

        Optional<User> userReturned = userService.findByUsername(user.getUsername());

        assertNotNull("Null user returned", userReturned);
        verify(userRepository, times(1)).findByUsername(anyString());
    }

    @Test
    public void validateUsernameTest() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("userName");
        Optional<User> userOptional = Optional.of(user);

        when(userRepository.findByUsername(anyString())).thenReturn(userOptional);

        assertFalse(userService.isUserNameValid("userName"));
    }
}
