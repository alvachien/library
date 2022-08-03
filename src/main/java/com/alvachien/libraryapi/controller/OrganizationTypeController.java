package com.alvachien.libraryapi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alvachien.libraryapi.exception.OrganizationTypeNotFoundException;
import com.alvachien.libraryapi.model.OrganizationType;
import com.alvachien.libraryapi.service.StorageService;

@RestController
public class OrganizationTypeController {
    private StorageService storage;

    public OrganizationTypeController(StorageService storage) {
        this.storage = storage;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/organizationtypes")
    Iterable<OrganizationType> all() {
        return this.storage.findOrganizationTypes();
    }
    // end::get-aggregate-root[]

    @PostMapping("/organizationtypes")
    OrganizationType createObject(@RequestBody OrganizationType newbook) {
        return this.storage.saveOrganizationType(newbook);
    }

    // Single item
    @GetMapping("/organizationtypes/{id}")
    OrganizationType single(@PathVariable Long id) {

        return this.storage.findOrganizationTypeByID(id)
                .orElseThrow(() -> new OrganizationTypeNotFoundException(id));
    }

    @PutMapping("/organizationtypes/{id}")
    OrganizationType updateObject(@RequestBody OrganizationType newBook, @PathVariable Long id) {

        return this.storage.findOrganizationTypeByID(id)
                .map(book -> {
                    book.setTypeName(newBook.getTypeName());
                    book.setTypeValue(newBook.getTypeValue());
                    return this.storage.saveOrganizationType(book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return storage.saveOrganizationType(newBook);
                });
    }

    @DeleteMapping("/organizationtypes/{id}")
    void deleteObject(@PathVariable Long id) {
        this.storage.deleteOrganizationTypeByID(id);
    }
    
}
