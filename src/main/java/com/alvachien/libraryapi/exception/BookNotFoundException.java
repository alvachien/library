package com.alvachien.libraryapi.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
      super("Could not find book with ID: " + id);
    }
}
