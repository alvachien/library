package com.alvachien.libraryapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.alvachien.libraryapi.model.Person;


public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findByNativeName(String nativeName);

    Person findById(long id);
}
