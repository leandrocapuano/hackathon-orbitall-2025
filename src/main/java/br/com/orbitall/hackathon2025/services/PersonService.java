package br.com.orbitall.hackathon2025.services;

import br.com.orbitall.hackathon2025.canonicals.PersonInput;
import br.com.orbitall.hackathon2025.canonicals.PersonOutput;
import br.com.orbitall.hackathon2025.exceptions.ResourceNotFoundException;
import br.com.orbitall.hackathon2025.models.Person;
import br.com.orbitall.hackathon2025.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;

    public PersonOutput create(PersonInput input) {
        LocalDateTime now = LocalDateTime.now();

        Person person = new Person();

        person.setFullName(input.fullName());
        person.setAge(input.age());
        person.setDescription(input.description());
        person.setId(UUID.randomUUID());
        person.setCreatedAt(now);
        person.setUpdatedAt(now);
        person.setActive(true);

        repository.save(person);

        return new PersonOutput(
                person.getId(),
                person.getFullName(),
                person.getAge(),
                person.getDescription(),
                person.getCreatedAt(),
                person.getUpdatedAt(),
                person.isActive()
        );
    }

    public PersonOutput retrieve(UUID id) {
        Person person = repository
                .findById(id)
                .filter(Person::isActive)
                .orElseThrow(() -> new ResourceNotFoundException("Not found the resource (id: " + id + ")"));

        PersonOutput output = new PersonOutput(
                person.getId(),
                person.getFullName(),
                person.getAge(),
                person.getDescription(),
                person.getCreatedAt(),
                person.getUpdatedAt(),
                person.isActive());

        return output;
    }

    public PersonOutput update(UUID id, PersonInput input) {
        Person fetched = repository
                .findById(id)
                .filter(Person::isActive)
                .orElseThrow(() -> new ResourceNotFoundException("Not found the resource (id: " + id + ")"));

        fetched.setFullName(input.fullName());
        fetched.setAge(input.age());
        fetched.setDescription(input.description());
        fetched.setUpdatedAt(LocalDateTime.now());

        repository.save(fetched);

        PersonOutput output = new PersonOutput(
                fetched.getId(),
                fetched.getFullName(),
                fetched.getAge(),
                fetched.getDescription(),
                fetched.getCreatedAt(),
                fetched.getUpdatedAt(),
                fetched.isActive());

        return output;
    }

    public PersonOutput delete(UUID id) {
        Person fetched = repository
                .findById(id)
                .filter(Person::isActive)
                .orElseThrow(() -> new ResourceNotFoundException("Not found the resource (id: " + id + ")"));

        fetched.setUpdatedAt(LocalDateTime.now());
        fetched.setActive(false);

        repository.save(fetched);

        return new PersonOutput(
                fetched.getId(),
                fetched.getFullName(),
                fetched.getAge(),
                fetched.getDescription(),
                fetched.getCreatedAt(),
                fetched.getUpdatedAt(),
                fetched.isActive());
    }

    public List<PersonOutput> findAll() {
        List<PersonOutput> list = new ArrayList<>();

        repository.findAll().forEach( person -> {

            if(person.isActive()) {
                PersonOutput output = new PersonOutput(
                        person.getId(),
                        person.getFullName(),
                        person.getAge(),
                        person.getDescription(),
                        person.getCreatedAt(),
                        person.getUpdatedAt(),
                        person.isActive());

                list.add(output);
            }
        });

        return list;
    }

}
