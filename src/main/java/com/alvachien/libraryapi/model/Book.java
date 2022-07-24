package com.alvachien.libraryapi.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private String nativeName;
    private String chineseName;
    private String detail;
    
    // @OneToMany(mappedBy="school", cascade = CascadeType.PERSIST)
    // private List<BookAuthor> authors;
}

