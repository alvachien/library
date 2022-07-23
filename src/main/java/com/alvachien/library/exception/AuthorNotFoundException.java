package com.alvachien.library.exception;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(Long id) {
      super("Could not find Author with ID: " + id);
    }
}
