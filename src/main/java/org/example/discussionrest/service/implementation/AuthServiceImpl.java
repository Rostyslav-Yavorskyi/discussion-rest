package org.example.discussionrest.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.discussionrest.dto.TokenReadDto;
import org.example.discussionrest.dto.UserLoginDto;
import org.example.discussionrest.dto.UserRegisterDto;
import org.example.discussionrest.entity.User;
import org.example.discussionrest.exception.TokenExpiredException;
import org.example.discussionrest.exception.UserAlreadyRegisteredException;
import org.example.discussionrest.exception.UserNotFoundException;
import org.example.discussionrest.mapper.UserMapper;
import org.example.discussionrest.service.AuthService;
import org.example.discussionrest.service.JwtService;
import org.example.discussionrest.util.ExceptionUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@AllArgsConstructor
public class AuthServiceImpl extends BaseService implements AuthService {

    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final ExceptionUtil exceptionUtil;

    @Override
    @Transactional
    public TokenReadDto register(UserRegisterDto userRegisterDto) throws UserAlreadyRegisteredException {
        if (userDao.findByEmail(userRegisterDto.getEmail()).isPresent()) {
            throw exceptionUtil.createUserAlreadyRegisteredException(userRegisterDto.getEmail());
        }

        User user = userMapper.toEntity(userRegisterDto);
        user.setRole(User.Role.USER);
        userDao.insert(user);
        return jwtService.generateToken(user);
    }

    @Override
    public TokenReadDto login(UserLoginDto userLoginDto) throws UserNotFoundException {
        User user = userDao.findByEmail(userLoginDto.getEmail())
                .orElseThrow(() -> exceptionUtil.createUserNotFoundException(userLoginDto.getEmail()));

        if (user.getPassword().equals(userLoginDto.getPassword())) {
            return jwtService.generateToken(user);
        }

        throw new UserNotFoundException("Incorrect password");
    }

    @Override
    public void authenticate(String token) throws TokenExpiredException, UserNotFoundException {
        if (jwtService.isTokenInvalid(token)) {
            throw new TokenExpiredException();
        }
        int userId = jwtService.extractId(token);
        User user = findUserByIdOrElseThrowException(userId, FetchType.DEFAULT);
        SecurityContextHolder.getContext().setAuthentication(createAuthentication(user));
    }

    private Authentication createAuthentication(User user) {
        Collection<? extends GrantedAuthority> authorities = Collections.singleton(() -> user.getRole().name());
        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }
}
