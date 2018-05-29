package com.tuncerb.converters;

import com.tuncerb.commands.UserCommand;
import com.tuncerb.domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by tuncer on 29/05/2018.
 */
public class UserToUserCommandTest {
    public static final Long ID_VALUE = 1L;
    public static final String NAME = "userName";
    UserToUserCommand convter;

    @Before
    public void setUp() throws Exception {
        convter = new UserToUserCommand();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(convter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(convter.convert(new User()));
    }

    @Test
    public void convert() throws Exception {
        User user = new User();
        user.setId(ID_VALUE);
        user.setUsername(NAME);

        UserCommand userCommand = convter.convert(user);

        assertEquals(ID_VALUE, userCommand.getId());
        assertEquals(NAME, userCommand.getUsername());

    }
}
