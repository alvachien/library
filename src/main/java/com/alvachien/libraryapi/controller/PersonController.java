package com.alvachien.libraryapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alvachien.libraryapi.exception.PersonNotFoundException;
import com.alvachien.libraryapi.model.Person;
import com.alvachien.libraryapi.repository.PersonRepository;

@RestController
public class PersonController {
  @Autowired
  private PersonRepository repository;

  public PersonController() {
  }

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/persons")
  Iterable<Person> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/persons")
  Person newAuthor(@RequestBody Person newPerson) {
    return repository.save(newPerson);
  }

  // Single item

  @GetMapping("/persons/{id}")
  Person one(@PathVariable Long id) {

    return repository.findById(id)
        .orElseThrow(() -> new PersonNotFoundException(id));
  }

  @PutMapping("/persons/{id}")
  Person replaceAuthor(@RequestBody Person newPerson, @PathVariable Long id) {

    return repository.findById(id)
        .map(author -> {
          author.setNativeName(newPerson.getNativeName());
          author.setChineseName(newPerson.getChineseName());
          author.setDetail(newPerson.getDetail());
          return repository.save(author);
        })
        .orElseGet(() -> {
          newPerson.setId(id);
          return repository.save(newPerson);
        });
  }

  @DeleteMapping("/persons/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
