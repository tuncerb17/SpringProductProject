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
public class UserCommandToUserTest {
    public static final Long ID_VALUE = 1L;
    public static final String NAME = "userName";
    UserCommandToUser conveter;

    @Before
    public void setUp() throws Exception {
        conveter = new UserCommandToUser();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(conveter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(conveter.convert(new UserCommand()));
    }

    @Test
    public void convert() throws Exception {
        UserCommand userCommand = new UserCommand();
        userCommand.setId(ID_VALUE);
        userCommand.setUsername(NAME);

        User user = conveter.convert(userCommand);

        assertEquals(ID_VALUE, user.getId());
        assertEquals(NAME, user.getUsername());
    }
}
