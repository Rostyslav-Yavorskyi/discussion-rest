package org.example.discussionrest.controller;

import lombok.AllArgsConstructor;
import org.example.discussionrest.dto.DiscussionCreateDto;
import org.example.discussionrest.dto.DiscussionReadDto;
import org.example.discussionrest.dto.DiscussionUpdateDto;
import org.example.discussionrest.exception.AuditoriumNotFoundException;
import org.example.discussionrest.exception.DiscussionNotFoundException;
import org.example.discussionrest.service.DiscussionService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discussion")
@AllArgsConstructor
public class DiscussionController {

    private final DiscussionService discussionService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DiscussionReadDto insert(@RequestBody DiscussionCreateDto discussionCreateDto) throws AuditoriumNotFoundException {
        return discussionService.insert(discussionCreateDto);
    }

    @GetMapping
    public List<DiscussionReadDto> findAll() {
        return discussionService.findAll();
    }

    @GetMapping("/{id}")
    public DiscussionReadDto findOne(@PathVariable int id) throws DiscussionNotFoundException {
        return discussionService.findOne(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody DiscussionUpdateDto discussionUpdateDto) throws DiscussionNotFoundException, AuditoriumNotFoundException {
        discussionService.update(id, discussionUpdateDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) throws DiscussionNotFoundException {
        discussionService.delete(id);
    }
}
