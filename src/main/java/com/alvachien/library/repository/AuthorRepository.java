package com.alvachien.library.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.alvachien.library.model.Author;


public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findByNativeName(String nativeName);

    Author findById(long id);
}
