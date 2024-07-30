package org.example.discussionrest.entity;

import lombok.Getter;
import org.example.discussionrest.dto.UserLoginDto;
import org.example.discussionrest.dto.UserRegisterDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private User.Role role;

    public CustomUserDetails(User user) {
        username = user.getEmail();
        password = user.getPassword();
        role = user.getRole();
    }

    public CustomUserDetails(UserLoginDto userLoginDto) {
        username = userLoginDto.getEmail();
        password = userLoginDto.getPassword();
    }

    public CustomUserDetails(UserRegisterDto userRegisterDto) {
        username = userRegisterDto.getEmail();
        password = userRegisterDto.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role::name);
    }
}
