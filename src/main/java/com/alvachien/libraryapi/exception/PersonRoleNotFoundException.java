package com.alvachien.libraryapi.exception;

public class PersonRoleNotFoundException extends RuntimeException {

    public PersonRoleNotFoundException(Long id) {
      super("Could not find person role with ID: " + id);
    }
    
}
