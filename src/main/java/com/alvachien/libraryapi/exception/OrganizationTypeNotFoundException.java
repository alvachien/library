package com.alvachien.libraryapi.exception;

public class OrganizationTypeNotFoundException extends RuntimeException {

    public OrganizationTypeNotFoundException(Long id) {
      super("Could not find organization type with ID: " + id);
    }
    
}
