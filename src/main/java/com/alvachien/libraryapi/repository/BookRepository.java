package com.alvachien.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alvachien.libraryapi.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}
