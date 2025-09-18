package br.com.orbitall.hackathon2025.controllers;

import br.com.orbitall.hackathon2025.models.Person;
import br.com.orbitall.hackathon2025.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonService service;

    @PostMapping
    public Person create(@RequestBody Person person) {
        return service.create(person);
    }

    @GetMapping("/{id}")
    public Person retrieve(@PathVariable UUID id) {
        return service.retrieve(id);
    }

    @PutMapping("/{id}")
    public Person update(@PathVariable UUID id, @RequestBody Person person) {
        return service.update(id, person);
    }

    @DeleteMapping("/{id}")
    public Person delete(@PathVariable UUID id) {
        return service.delete(id);
    }

}
