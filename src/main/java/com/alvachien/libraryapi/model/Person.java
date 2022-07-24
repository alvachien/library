package com.alvachien.libraryapi.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private String nativeName;
    private String chineseName;
    private String detail;

    Person() {
    }

    Person(String nativeName, String chineseName, String detail) {
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

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Person))
            return false;
        Person author = (Person) o;
        return Objects.equals(this.id, author.id) && Objects.equals(this.nativeName, author.nativeName)
                && Objects.equals(this.chineseName, author.chineseName) && Objects.equals(this.detail, author.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nativeName, this.chineseName, this.detail);
    }

    @Override
    public String toString() {
        return "Author{" + "id=" + this.id + ", nativeName='" + this.nativeName + '\'' + ", chineseName='"
                + this.chineseName + '\'' + '}';
    }
}
