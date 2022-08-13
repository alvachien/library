package com.alvachien.libraryapi.storage.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "person_role_def")
public class PersonRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id")
    private Long id;

    @Column(name="role_value", nullable = false)
    @Convert(converter = PersonRoleEnumConverter.class)
    private PersonRoleEnum roleValue;

    @Column(name="role_name", nullable = true, length = 50)
    private String roleName;

    @ManyToMany(fetch=FetchType.LAZY, mappedBy="roles")
    private List<Person> persons;

    public PersonRole() {
        this.persons = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonRoleEnum getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(PersonRoleEnum roleValue) {
        this.roleValue = roleValue;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Person> getPersons() {
        return persons;
    }
   
}
