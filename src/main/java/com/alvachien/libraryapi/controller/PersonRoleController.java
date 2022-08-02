package com.alvachien.libraryapi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alvachien.libraryapi.exception.PersonNotFoundException;
import com.alvachien.libraryapi.exception.PersonRoleNotFoundException;
import com.alvachien.libraryapi.model.PersonRole;
import com.alvachien.libraryapi.service.StorageService;

@RestController
public class PersonRoleController {
    private StorageService storage;

    public PersonRoleController(StorageService storage) {
        this.storage = storage;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/personroles")
    Iterable<PersonRole> all() {
        return this.storage.findPersonRoles();
    }
    // end::get-aggregate-root[]

    @PostMapping("/personroles")
    PersonRole createPersonRole(@RequestBody PersonRole newbook) {
        return this.storage.savePersonRole(newbook);
    }

    // Single item
    @GetMapping("/personroles/{id}")
    PersonRole one(@PathVariable Long id) {

        return this.storage.findPersonRoleByID(id)
                .orElseThrow(() -> new PersonRoleNotFoundException(id));
    }

    @PutMapping("/personroles/{id}")
    PersonRole updateObject(@RequestBody PersonRole newBook, @PathVariable Long id) {

        return this.storage.findPersonRoleByID(id)
                .map(bookctgy -> {
                    bookctgy.setRoleName(newBook.getRoleName());
                    bookctgy.setRoleValue(newBook.getRoleValue());
                    return this.storage.savePersonRole(bookctgy);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return storage.savePersonRole(newBook);
                });
    }

    @DeleteMapping("/personroles/{id}")
    void deleteObject(@PathVariable Long id) {
        this.storage.deletePersonRoleByID(id);
    }
    
}
