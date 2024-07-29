package org.example.discussionrest.controller;

import lombok.AllArgsConstructor;
import org.example.discussionrest.entity.Auditorium;
import org.example.discussionrest.exception.RecordNotFoundException;
import org.example.discussionrest.service.AuditoriumService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auditorium")
@AllArgsConstructor
public class AuditoriumController {

    private final AuditoriumService auditoriumService;

    @GetMapping
    public List<Auditorium> findAll() {
        return auditoriumService.findAll();
    }

    @GetMapping("/{id}")
    public Auditorium findById(@PathVariable int id) throws RecordNotFoundException {
        return auditoriumService.findOne(id);
    }
}
