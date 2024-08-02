package org.example.discussionrest.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.discussionrest.dto.UserInternalDto;
import org.example.discussionrest.dto.UserReadDto;
import org.example.discussionrest.dto.UserRegisterDto;
import org.example.discussionrest.dto.UserUpdateDto;
import org.example.discussionrest.entity.Discussion;
import org.example.discussionrest.entity.User;
import org.example.discussionrest.exception.DiscussionNotFoundException;
import org.example.discussionrest.exception.UserAlreadyJoinedToDiscussionException;
import org.example.discussionrest.exception.UserAlreadyRegisteredException;
import org.example.discussionrest.exception.UserNotFoundException;
import org.example.discussionrest.mapper.UserMapper;
import org.example.discussionrest.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl extends BaseService implements UserService {

    private final UserMapper userMapper;

    @Override
    @Transactional
    public void register(UserRegisterDto userRegisterDto) throws UserAlreadyRegisteredException {
        if (userDao.findByEmail(userRegisterDto.getEmail()).isPresent()) {
            throw exceptionUtil.createUserAlreadyRegisteredException(userRegisterDto.getEmail());
        }

        User user = userMapper.toEntity(userRegisterDto);
        user.setRole(User.Role.USER);
        userDao.insert(user);
    }

    @Override
    public List<UserReadDto> findAll() {
        return userMapper.toReadDto(userDao.findAll());
    }

    @Override
    public List<UserReadDto> findAllByDiscussionId(int discussionId) throws DiscussionNotFoundException {
        return userMapper.toReadDto(findDiscussionByIdOrElseThrowException(discussionId, FetchType.WITH_USERS).getUsers());
    }

    @Override
    public UserReadDto findOne(int id) throws UserNotFoundException {
        User user = findUserByIdOrElseThrowException(id, FetchType.DEFAULT);
        return userMapper.toReadDto(user);
    }

    @Override
    @Transactional
    public UserInternalDto findByEmail(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Authentication failed"));
        return userMapper.toInternalDto(user);
    }

    @Override
    @Transactional
    public void update(int id, UserUpdateDto userUpdateDto) throws UserNotFoundException {
        User user = findUserByIdOrElseThrowException(id, FetchType.DEFAULT);
        userMapper.update(user, userUpdateDto);
    }

    @Override
    @Transactional
    public void joinToDiscussion(int discussionId) throws DiscussionNotFoundException, UserAlreadyJoinedToDiscussionException, UserNotFoundException {
        Discussion discussion = findDiscussionByIdOrElseThrowException(discussionId, FetchType.DEFAULT);
        UserInternalDto userInternalDto = (UserInternalDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = findUserByIdOrElseThrowException(userInternalDto.getId(), FetchType.WITH_DISCUSSIONS);
        if (user.discussionExists(discussion)) {
            throw exceptionUtil.createUserAlreadyJoinedToDiscussionException();
        }
        user.addDiscussion(discussion);
    }

    @Override
    @Transactional
    public void leaveFromDiscussion(int discussionId) throws DiscussionNotFoundException, UserNotFoundException {
        Discussion discussion = findDiscussionByIdOrElseThrowException(discussionId, FetchType.DEFAULT);
        UserInternalDto userInternalDto = (UserInternalDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = findUserByIdOrElseThrowException(userInternalDto.getId(), FetchType.WITH_DISCUSSIONS);
        boolean deleted = user.removeDiscussion(discussion);
        if (!deleted) {
            throw exceptionUtil.createDiscussionNotFoundExceptionWithUserId(discussion.getId(), user.getId());
        }
    }

    @Override
    @Transactional
    public void delete(int id) throws UserNotFoundException {
        if (!userDao.delete(id)) {
            throw exceptionUtil.createUserNotFoundException(id);
        }
    }
}
