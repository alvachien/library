package com.alvachien.libraryapi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private String nativeName;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getOriginLanguage() {
        return originLanguage;
    }

    public void setOriginLanguage(String originLanguage) {
        this.originLanguage = originLanguage;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public void setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
    }

    public int getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(int releasedYear) {
        this.releasedYear = releasedYear;
    }

    private String chineseName;
    private String detail;

    private String originLanguage;
    private String bookLanguage;

    private int releasedYear;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="book_author", 
        joinColumns={@JoinColumn(name="book_id",referencedColumnName="id")},
        inverseJoinColumns={@JoinColumn(name="author_id",referencedColumnName="id")} )
    private List<Person> authors;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="book_author", 
        joinColumns={@JoinColumn(name="book_id",referencedColumnName="id")},
        inverseJoinColumns={@JoinColumn(name="author_id",referencedColumnName="id")} )
    private List<Person> translators;

    public List<Person> getAuthors() {
        return authors;
    }

    public List<Person> getTranslators() {
        return translators;
    }
}

