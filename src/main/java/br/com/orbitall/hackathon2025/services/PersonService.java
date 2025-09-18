package br.com.orbitall.hackathon2025.services;

import br.com.orbitall.hackathon2025.models.Person;
import br.com.orbitall.hackathon2025.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;

    public Person create(Person person) {
        LocalDateTime now = LocalDateTime.now();

        person.setId(UUID.randomUUID());
        person.setCreatedAt(now);
        person.setUpdatedAt(now);
        person.setActive(true);

        return repository.save(person);
    }

    public Person retrieve(UUID id) {
        return repository.findById(id).get();
    }

    public Person update(UUID id, Person person) {
        Person fetched = repository.findById(id).get();

        fetched.setFullName(person.getFullName());
        fetched.setAge(person.getAge());
        fetched.setDescription(person.getDescription());
        fetched.setUpdatedAt(LocalDateTime.now());

        return repository.save(fetched);
    }

    public Person delete(UUID id) {
        Person fetched = repository.findById(id).get();

        fetched.setUpdatedAt(LocalDateTime.now());
        fetched.setActive(false);

        return repository.save(fetched);
    }

}
