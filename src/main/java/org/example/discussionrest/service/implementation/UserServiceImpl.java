package org.example.discussionrest.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.discussionrest.dto.SortDto;
import org.example.discussionrest.dto.UserReadDto;
import org.example.discussionrest.dto.UserUpdateDto;
import org.example.discussionrest.entity.Discussion;
import org.example.discussionrest.entity.User;
import org.example.discussionrest.exception.DiscussionNotFoundException;
import org.example.discussionrest.exception.UserAlreadyJoinedToDiscussionException;
import org.example.discussionrest.exception.UserNotFoundException;
import org.example.discussionrest.mapper.UserMapper;
import org.example.discussionrest.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl extends BaseService implements UserService {

    private final UserMapper userMapper;

    @Override
    public List<UserReadDto> findAll(SortDto sortDto) {
        return userMapper.toReadDto(userDao.findAll(sortDto));
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
    public void update(int id, UserUpdateDto userUpdateDto) throws UserNotFoundException {
        User user = findUserByIdOrElseThrowException(id, FetchType.DEFAULT);
        userMapper.update(user, userUpdateDto);
    }

    @Override
    @Transactional
    public void joinToDiscussion(int discussionId) throws DiscussionNotFoundException, UserAlreadyJoinedToDiscussionException, UserNotFoundException {
        Discussion discussion = findDiscussionByIdOrElseThrowException(discussionId, FetchType.DEFAULT);
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = findUserByIdOrElseThrowException(authenticatedUser.getId(), FetchType.WITH_DISCUSSIONS);
        if (user.discussionExists(discussion)) {
            throw exceptionUtil.createUserAlreadyJoinedToDiscussionException();
        }
        user.addDiscussion(discussion);
    }

    @Override
    @Transactional
    public void leaveFromDiscussion(int discussionId) throws DiscussionNotFoundException, UserNotFoundException {
        Discussion discussion = findDiscussionByIdOrElseThrowException(discussionId, FetchType.DEFAULT);
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = findUserByIdOrElseThrowException(authenticatedUser.getId(), FetchType.WITH_DISCUSSIONS);
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
