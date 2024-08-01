package org.example.discussionrest.controller;

import lombok.RequiredArgsConstructor;
import org.example.discussionrest.dto.*;
import org.example.discussionrest.entity.User;
import org.example.discussionrest.exception.DiscussionNotFoundException;
import org.example.discussionrest.exception.UserAlreadyJoinedToDiscussionException;
import org.example.discussionrest.exception.UserAlreadyRegisteredException;
import org.example.discussionrest.exception.UserNotFoundException;
import org.example.discussionrest.mapper.UserMapper;
import org.example.discussionrest.service.JwtService;
import org.example.discussionrest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public TokenReadDto login(@RequestBody UserLoginDto userLoginDto) throws UsernameNotFoundException {
        Authentication authentication = new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());
        authentication = authenticationManager.authenticate(authentication);
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userLoginDto);
        }

        throw new UsernameNotFoundException("Invalid email or password");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public TokenReadDto register(@RequestBody UserRegisterDto userRegisterDto) throws UserAlreadyRegisteredException {
        userService.register(userRegisterDto);
        return jwtService.generateToken(userRegisterDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/discussion/{id}")
    public void joinToDiscussion(@PathVariable int id) throws DiscussionNotFoundException, UserAlreadyJoinedToDiscussionException {
        userService.joinToDiscussion(id);
    }

    @GetMapping("/me")
    public UserReadDto getMe() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userMapper.toReadDto(user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<UserReadDto> findAll() {
        return userService.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public UserReadDto findOne(@PathVariable int id) throws UserNotFoundException {
        return userService.findOne(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody UserUpdateDto userUpdateDto) throws UserNotFoundException {
        userService.update(id, userUpdateDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) throws UserNotFoundException {
        userService.delete(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/discussion/{id}")
    public void leaveFromDiscussion(@PathVariable int id) throws DiscussionNotFoundException {
        userService.leaveFromDiscussion(id);
    }
}
