package org.example.discussionrest.controller;

import lombok.AllArgsConstructor;
import org.example.discussionrest.dto.AuditoriumCreateDto;
import org.example.discussionrest.dto.AuditoriumReadDto;
import org.example.discussionrest.dto.AuditoriumUpdateDto;
import org.example.discussionrest.exception.AuditoriumNotFoundException;
import org.example.discussionrest.service.AuditoriumService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auditorium")
@AllArgsConstructor
public class AuditoriumController {

    private final AuditoriumService auditoriumService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AuditoriumReadDto insert(@RequestBody AuditoriumCreateDto auditoriumCreateDto) {
        return auditoriumService.insertIfNotExists(auditoriumCreateDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<AuditoriumReadDto> findAll() {
        return auditoriumService.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public AuditoriumReadDto findOne(@PathVariable int id) throws AuditoriumNotFoundException {
        return auditoriumService.findOne(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody AuditoriumUpdateDto auditoriumUpdateDto) throws AuditoriumNotFoundException {
        auditoriumService.update(id, auditoriumUpdateDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) throws AuditoriumNotFoundException {
        auditoriumService.delete(id);
    }
}
