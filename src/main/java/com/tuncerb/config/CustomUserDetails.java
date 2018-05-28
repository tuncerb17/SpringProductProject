package com.tuncerb.config;

import com.tuncerb.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by tuncer on 27/05/2018.
 */
public class CustomUserDetails implements UserDetails {

    private User user;

    private CustomUserDetails(User user) {
        this.user = user;
    }

    public static CustomUserDetails of(User user) {
        if (user == null) {
            return null;
        }
        return new CustomUserDetails(user);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
}
