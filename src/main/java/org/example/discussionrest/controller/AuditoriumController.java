package org.example.discussionrest.controller;

import lombok.AllArgsConstructor;
import org.example.discussionrest.dto.AuditoriumCreateDto;
import org.example.discussionrest.dto.AuditoriumReadDto;
import org.example.discussionrest.exception.RecordNotFoundException;
import org.example.discussionrest.service.AuditoriumService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auditorium")
@AllArgsConstructor
public class AuditoriumController {

    private final AuditoriumService auditoriumService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AuditoriumReadDto insert(@RequestBody AuditoriumCreateDto auditoriumCreateDto) {
        return auditoriumService.insertIfNotExists(auditoriumCreateDto);
    }

    @GetMapping
    public List<AuditoriumReadDto> findAll() {
        return auditoriumService.findAll();
    }

    @GetMapping("/{id}")
    public AuditoriumReadDto findById(@PathVariable int id) throws RecordNotFoundException {
        return auditoriumService.findOne(id);
    }
}
