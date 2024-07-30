package org.example.discussionrest.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.discussionrest.dao.UserDao;
import org.example.discussionrest.dto.UserRegisterDto;
import org.example.discussionrest.entity.User;
import org.example.discussionrest.exception.UserAlreadyRegisteredException;
import org.example.discussionrest.exception.UserNotFoundException;
import org.example.discussionrest.mapper.UserMapper;
import org.example.discussionrest.service.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void register(UserRegisterDto userRegisterDto) throws UserAlreadyRegisteredException {
        if (userDao.findByEmail(userRegisterDto.getEmail()).isPresent()) {
            throw new UserAlreadyRegisteredException(userRegisterDto.getEmail());
        }

        User user = userMapper.toEntity(userRegisterDto);
        user.setRole(User.Role.USER);
        userDao.insert(user);
    }

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        return userDao.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }
}
