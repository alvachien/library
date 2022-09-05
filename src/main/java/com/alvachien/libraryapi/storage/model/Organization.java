package com.alvachien.libraryapi.storage.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@Table(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id")
    private Long id;
    @Column(name = "native_name", nullable = true, length = 200)
    private String nativeName;
    @Column(name = "chinese_name", nullable = true, length = 200)
    private String chineseName;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="organization_types", 
        joinColumns={ @JoinColumn(name = "org_id", referencedColumnName = "id") },
        inverseJoinColumns={ @JoinColumn(name="type_id",referencedColumnName="id") } )
    private Set<OrganizationType> orgtypes;

    @ManyToMany(mappedBy="publishHouses")
    public Set<Book> books;
    
    public Organization() {
        this.orgtypes = new HashSet<>();
        this.books = new HashSet<>();
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

    public Set<Book> getBooks() {
        return this.books;
    }

    public Set<OrganizationType> getOrganizationTypes() {
        return this.orgtypes;
    }

}
