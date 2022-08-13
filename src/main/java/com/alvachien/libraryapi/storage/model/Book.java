package com.alvachien.libraryapi.storage.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "native_name", length = 200, nullable = true)
    private String nativeName;
    @Column(name = "chinese_name", length = 200, nullable = true)
    private String chineseName;
    @Column(name = "detail", length = 200, nullable = true)
    private String detail;
    @Column(name = "isbn", length = 15, nullable = true)
    private String isbn;
    @Column(name = "page_count", nullable = true)
    private Integer pageCount;

    @Column(name = "origin_language", length = 5, nullable = true)
    private String originLanguage;
    @Column(name = "book_language", length = 5, nullable = true)
    private String bookLanguage;

    @Column(name = "released_year", nullable = true)
    private Integer releasedYear;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="book_author", 
        joinColumns={@JoinColumn(name="book_id",referencedColumnName="id")},
        inverseJoinColumns={@JoinColumn(name="author_id",referencedColumnName="id")} )
    private List<Person> authors;

    // @ManyToMany(fetch=FetchType.EAGER)
    // @JoinTable(
    //     name="book_translator", 
    //     joinColumns={@JoinColumn(name="book_id",referencedColumnName="id")},
    //     inverseJoinColumns={@JoinColumn(name="author_id",referencedColumnName="id")} )
    // private List<Person> translators;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="book_press", 
        joinColumns={@JoinColumn(name="book_id",referencedColumnName="id")},
        inverseJoinColumns={@JoinColumn(name="org_id",referencedColumnName="id")} )
    private List<Organization> publishHouses;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="book_category", 
        joinColumns={@JoinColumn(name="book_id",referencedColumnName="id")},
        inverseJoinColumns={@JoinColumn(name="ctgy_id",referencedColumnName="id")} )
    private List<BookCategory> categories;

    public Book() {
        this.authors = new ArrayList<>();
        // this.translators = new ArrayList<>();
        this.publishHouses = new ArrayList<>();
        this.categories = new ArrayList<>();
    }
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

    public void setReleasedYear(Integer releasedYear) {
        this.releasedYear = releasedYear;
    }
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<Organization> getPublishHouses() {
        return publishHouses;
    }

    public List<Person> getAuthors() {
        return authors;
    }

    // public List<Person> getTranslators() {
    //     return translators;
    // }

    public List<BookCategory> getBookCategories() {
        return this.categories;
    }
}

