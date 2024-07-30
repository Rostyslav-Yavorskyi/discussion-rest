package org.example.discussionrest.service.implementation;

import lombok.RequiredArgsConstructor;
import org.example.discussionrest.dto.UserSignInDto;
import org.example.discussionrest.dto.TokenReadDto;
import org.example.discussionrest.entity.User;
import org.example.discussionrest.exception.UserNotFoundException;
import org.example.discussionrest.service.AuthenticationService;
import org.example.discussionrest.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JwtServiceImpl jwtService;

    @Override
    public TokenReadDto signIn(UserSignInDto userSignInDto) throws UserNotFoundException {
        User user = userService.findByEmail(userSignInDto.getEmail());
        if (!user.getPassword().equals(userSignInDto.getPassword())) {
            throw new UserNotFoundException(userSignInDto.getEmail());
        }

        String token = jwtService.generateToken(user);
        return new TokenReadDto(token);
    }
}
