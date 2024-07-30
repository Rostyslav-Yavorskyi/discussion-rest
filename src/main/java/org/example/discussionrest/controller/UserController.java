package org.example.discussionrest.controller;

import lombok.RequiredArgsConstructor;
import org.example.discussionrest.dto.TokenReadDto;
import org.example.discussionrest.dto.UserSignInDto;
import org.example.discussionrest.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;

    @GetMapping
    public String getMe() {
        return "Hello World";
    }

    @PostMapping("/sign-in")
    public TokenReadDto singIn(@RequestBody UserSignInDto userSignInDto) {
        return authenticationService.signIn(userSignInDto);
    }
}
