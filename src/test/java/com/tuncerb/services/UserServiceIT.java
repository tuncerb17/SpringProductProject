package com.tuncerb.services;

import com.tuncerb.commands.UserCommand;
import com.tuncerb.converters.UserCommandToUser;
import com.tuncerb.converters.UserToUserCommand;
import com.tuncerb.domain.User;
import com.tuncerb.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * Created by tuncer on 29/05/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIT {
    public static final String NEW_USERNAME = "newusername";

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCommandToUser userCommandToUser;

    @Autowired
    UserToUserCommand userToUserCommand;

    @Transactional
    @Test
    public void testSaveUserCommand() throws Exception {
        User user = new User();
        user.setUsername("test");
        user.setPassword("password");
        userRepository.save(user);
        //given
        Iterable<User> users = userRepository.findAll();
        User testUser = users.iterator().next();
        UserCommand testUserCommand = userToUserCommand.convert(testUser);

        //when
        testUserCommand.setUsername(NEW_USERNAME);
        UserCommand savedUserCommand = userService.saveUserCommand(testUserCommand);

        //then
        assertEquals(NEW_USERNAME, savedUserCommand.getUsername());
        assertEquals(testUser.getId(), savedUserCommand.getId());
        assertEquals(testUser.getUsername(), savedUserCommand.getUsername());
    }
}
