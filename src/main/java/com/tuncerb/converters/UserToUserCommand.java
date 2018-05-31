package com.tuncerb.converters;

import com.tuncerb.commands.UserCommand;
import com.tuncerb.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by tuncer on 26/05/2018.
 */
@Component
public class UserToUserCommand implements Converter<User, UserCommand> {

    @Nullable
    @Override
    public UserCommand convert(User source) {
        if (source == null) {
            return null;
        }

        final UserCommand userCommand = new UserCommand();
        userCommand.setId(source.getId());
        userCommand.setUsername(source.getUsername());
        userCommand.setPassword(source.getPassword());
        return userCommand;
    }
}
