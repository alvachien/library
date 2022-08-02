package com.alvachien.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alvachien.libraryapi.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
