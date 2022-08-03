package com.alvachien.libraryapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alvachien.libraryapi.model.Book;
import com.alvachien.libraryapi.model.BookCategory;
import com.alvachien.libraryapi.model.Organization;
import com.alvachien.libraryapi.model.OrganizationType;
import com.alvachien.libraryapi.model.Person;
import com.alvachien.libraryapi.model.PersonRole;
import com.alvachien.libraryapi.repository.BookCategoryRepository;
import com.alvachien.libraryapi.repository.BookRepository;
import com.alvachien.libraryapi.repository.OrganizationRepository;
import com.alvachien.libraryapi.repository.OrganizationTypeRepository;
import com.alvachien.libraryapi.repository.PersonRepository;
import com.alvachien.libraryapi.repository.PersonRoleRepository;

@Service
public class StorageService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookCategoryRepository bookCategoryRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private OrganizationTypeRepository organizationTypeRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonRoleRepository personRoleRepository;
    
    ///
    /// Books
    ///
    public List<Book> findBooks() {
        return this.bookRepository.findAll();
    }
    public Book saveBook(Book book) {
        return this.bookRepository.save(book);
    }
    public Optional<Book> findBookByID(Long id) {
        return this.bookRepository.findById(id);
    }
    public void deleteBookByID(Long id) {
        this.bookRepository.deleteById(id);
    }
    public void deleteAllBooks() {
        this.bookRepository.deleteAll();
    }

    ///
    /// Book categories
    ///
    public List<BookCategory> findBookCategories() {
        return this.bookCategoryRepository.findAll();
    }
    public BookCategory saveBookCategory(BookCategory bookCategory) {
        return this.bookCategoryRepository.save(bookCategory);
    }
    public Optional<BookCategory> findBookCategoryByID(Long id) {
        return this.bookCategoryRepository.findById(id);
    }
    public void deleteBookCategoryByID(Long id) {
        this.bookCategoryRepository.deleteById(id);
    }

    ///
    /// Organizations
    ///
    public List<Organization> findOrganizations() {
        return this.organizationRepository.findAll();
    }
    public Organization saveOrganization(Organization organization) {
        return this.organizationRepository.save(organization);
    }
    public Optional<Organization> findOrganizationByID(Long id) {
        return this.organizationRepository.findById(id);
    }
    public void deleteOrganizationByID(Long id) {
        this.organizationRepository.deleteById(id);
    }

    ///
    /// Organization types
    ///
    public List<OrganizationType> findOrganizationTypes() {
        return this.organizationTypeRepository.findAll();
    }
    public OrganizationType saveOrganizationType(OrganizationType organization) {
        return this.organizationTypeRepository.save(organization);
    }
    public Optional<OrganizationType> findOrganizationTypeByID(Long id) {
        return this.organizationTypeRepository.findById(id);
    }
    public void deleteOrganizationTypeByID(Long id) {
        this.organizationTypeRepository.deleteById(id);
    }

    ///
    /// Person
    ///
    public List<Person> findPersons() {
        return this.personRepository.findAll();
    }
    public Person savePerson(Person person) {
        return this.personRepository.save(person);
    }
    public Optional<Person> findPersonByID(Long id) {
        return this.personRepository.findById(id);
    }
    public void deletePersonByID(Long id) {
        this.personRepository.deleteById(id);
    }

    ///
    /// Person roles
    ///
    public List<PersonRole> findPersonRoles() {
        return this.personRoleRepository.findAll();
    }
    public PersonRole savePersonRole(PersonRole personRole) {
        return this.personRoleRepository.save(personRole);
    }
    public Optional<PersonRole> findPersonRoleByID(Long id) {
        return this.personRoleRepository.findById(id);
    }
    public void deletePersonRoleByID(Long id) {
        this.personRoleRepository.deleteById(id);
    }
}
