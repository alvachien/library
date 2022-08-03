package com.alvachien.libraryapi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alvachien.libraryapi.exception.OrganizationNotFoundException;
import com.alvachien.libraryapi.model.Organization;
import com.alvachien.libraryapi.service.StorageService;

@RestController
public class OrganizationController {
    private StorageService storage;

    public OrganizationController(StorageService storage) {
        this.storage = storage;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/organizations")
    Iterable<Organization> all() {
        return this.storage.findOrganizations();
    }
    // end::get-aggregate-root[]

    @PostMapping("/organizations")
    Organization createObject(@RequestBody Organization newbook) {
        return this.storage.saveOrganization(newbook);
    }

    // Single item
    @GetMapping("/organizations/{id}")
    Organization single(@PathVariable Long id) {

        return this.storage.findOrganizationByID(id)
                .orElseThrow(() -> new OrganizationNotFoundException(id));
    }

    @PutMapping("/organizations/{id}")
    Organization updateObject(@RequestBody Organization newBook, @PathVariable Long id) {

        return this.storage.findOrganizationByID(id)
                .map(book -> {
                    book.setNativeName(newBook.getNativeName());
                    book.setChineseName(newBook.getChineseName());
                    return this.storage.saveOrganization(book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return storage.saveOrganization(newBook);
                });
    }

    @DeleteMapping("/organizations/{id}")
    void deleteObject(@PathVariable Long id) {
        this.storage.deleteOrganizationByID(id);
    }
    
}
