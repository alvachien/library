package com.alvachien.libraryapi.exception;

public class OrganizationNotFoundException extends RuntimeException {

    public OrganizationNotFoundException(Long id) {
      super("Could not find organization with ID: " + id);
    }
}
