package com.alvachien.libraryapi.storage.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") 
    private Long id;
    @Column(name = "native_name", nullable = true, length = 200)
    private String nativeName;
    @Column(name = "chinese_name", nullable = true, length = 200)
    private String chineseName;
    
    @Column(name = "detail", nullable = true, length = 200)
    private String detail;

    @ManyToMany(fetch=FetchType.EAGER, mappedBy="authors")
    private List<Book> books;

    // @ManyToMany(fetch=FetchType.EAGER, mappedBy="translators")
    // private List<Book> translatedbooks;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="person_roles", 
        joinColumns={ @JoinColumn(name = "person_id", referencedColumnName = "id") },
        inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName = "id") } )
    private List<PersonRole> roles;

    public Person() {
        this.roles = new ArrayList<>();
        this.books = new ArrayList<>();
    }

    public Person(String nativeName, String chineseName, String detail) {
        this();
        this.nativeName = nativeName;
        this.chineseName = chineseName;
        this.detail = detail;
    }

    public Long getId() {
        return this.id;
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

    public List<Book> getBooks() {
        return books;
    }

    // public List<Book> getTranslatedbooks() {
    //     return translatedbooks;
    // }

    public List<PersonRole> getRoles() {
        return this.roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Person))
            return false;
        Person person = (Person) o;
        return Objects.equals(this.id, person.id) && Objects.equals(this.nativeName, person.nativeName)
                && Objects.equals(this.chineseName, person.chineseName) && Objects.equals(this.detail, person.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nativeName, this.chineseName, this.detail);
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + this.id + ", nativeName='" + this.nativeName + '\'' + ", chineseName='"
                + this.chineseName + '\'' + '}';
    }
}
