package org.example.discussionrest.controller;

import lombok.RequiredArgsConstructor;
import org.example.discussionrest.dto.DiscussionReadDto;
import org.example.discussionrest.dto.UserReadDto;
import org.example.discussionrest.dto.UserUpdateDto;
import org.example.discussionrest.entity.User;
import org.example.discussionrest.exception.UserNotFoundException;
import org.example.discussionrest.mapper.UserMapper;
import org.example.discussionrest.service.DiscussionService;
import org.example.discussionrest.service.UserService;
import org.example.discussionrest.util.SortDtoBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final DiscussionService discussionService;
    private final UserMapper userMapper;
    private final SortDtoBuilder sortDtoBuilder;

    @GetMapping("/discussions")
    public List<DiscussionReadDto> findDiscussions() throws UserNotFoundException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return discussionService.findAllByUserId(user.getId());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}/discussions")
    public List<DiscussionReadDto> findDiscussions(@PathVariable int id) throws UserNotFoundException {
        return discussionService.findAllByUserId(id);
    }

    @GetMapping("/me")
    public UserReadDto getMe() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userMapper.toReadDto(user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<UserReadDto> findAll(@RequestParam(defaultValue = "+id") String sort) {
        return userService.findAll(sortDtoBuilder.buildSortDto(sort));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public UserReadDto findOne(@PathVariable int id) throws UserNotFoundException {
        return userService.findOne(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody @Validated UserUpdateDto userUpdateDto) throws UserNotFoundException {
        userService.update(id, userUpdateDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) throws UserNotFoundException {
        userService.delete(id);
    }
}
