package com.tuncerb.converters;

import com.tuncerb.commands.UserCommand;
import com.tuncerb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by tuncer on 26/05/2018.
 */
@Component
public class UserCommandToUser implements Converter<UserCommand, User> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Nullable
    @Override
    public User convert(UserCommand source) {
        if (source == null) {
            return null;
        }

        final User user = new User();
        user.setId(source.getId());
        user.setUsername(source.getUsername());
        if(source.getPassword() != null){
            user.setPassword(passwordEncoder.encode(source.getPassword()));
        }
        return user;
    }
}
