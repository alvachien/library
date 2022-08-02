package com.alvachien.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alvachien.libraryapi.model.BookCategory;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {    
}
