package com.alvachien.libraryapi.exception;

public class BookCategoryNotFoundException extends RuntimeException {

    public BookCategoryNotFoundException(Long id) {
      super("Could not find book category with ID: " + id);
    }
    
}
