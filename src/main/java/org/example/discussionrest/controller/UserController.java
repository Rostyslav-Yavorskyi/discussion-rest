package org.example.discussionrest.controller;

import lombok.RequiredArgsConstructor;
import org.example.discussionrest.dto.TokenReadDto;
import org.example.discussionrest.dto.UserLoginDto;
import org.example.discussionrest.dto.UserRegisterDto;
import org.example.discussionrest.entity.CustomUserDetails;
import org.example.discussionrest.exception.UserAlreadyRegisteredException;
import org.example.discussionrest.service.JwtService;
import org.example.discussionrest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    @GetMapping("/me")
    public String getMe() {
        return "Hello World";
    }

    @PostMapping("/login")
    public TokenReadDto login(@RequestBody UserLoginDto userLoginDto) throws UsernameNotFoundException {
        Authentication authentication = new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());
        authentication = authenticationManager.authenticate(authentication);
        if (authentication.isAuthenticated()) {
            return new TokenReadDto(jwtService.generateToken(new CustomUserDetails(userLoginDto)));
        }

        throw new UsernameNotFoundException("Invalid email or password");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public TokenReadDto register(@RequestBody UserRegisterDto userRegisterDto) throws UserAlreadyRegisteredException {
        userService.register(userRegisterDto);
        return new TokenReadDto(jwtService.generateToken(new CustomUserDetails(userRegisterDto)));
    }
}
