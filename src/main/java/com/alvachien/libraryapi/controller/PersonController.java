package com.alvachien.libraryapi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alvachien.libraryapi.exception.PersonNotFoundException;
import com.alvachien.libraryapi.model.Person;
import com.alvachien.libraryapi.service.StorageService;

@RestController
public class PersonController {
  private StorageService storage;

  public PersonController(StorageService storage) {
    this.storage = storage;
  }

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/persons")
  Iterable<Person> all() {
    return this.storage.findPersons();
  }
  // end::get-aggregate-root[]

  @PostMapping("/persons")
  Person newAuthor(@RequestBody Person newPerson) {
    return this.storage.savePerson(newPerson);
  }

  // Single item
  @GetMapping("/persons/{id}")
  Person one(@PathVariable Long id) {

    return this.storage.findPersonByID(id)
        .orElseThrow(() -> new PersonNotFoundException(id));
  }

  @PutMapping("/persons/{id}")
  Person replaceAuthor(@RequestBody Person newPerson, @PathVariable Long id) {

    return this.storage.findPersonByID(id)
        .map(author -> {
          author.setNativeName(newPerson.getNativeName());
          author.setChineseName(newPerson.getChineseName());
          author.setDetail(newPerson.getDetail());
          return this.storage.savePerson(author);
        })
        .orElseGet(() -> {
          newPerson.setId(id);
          return this.storage.savePerson(newPerson);
        });
  }

  @DeleteMapping("/persons/{id}")
  void deleteEmployee(@PathVariable Long id) {
    this.storage.deletePersonByID(id);
  }
}
