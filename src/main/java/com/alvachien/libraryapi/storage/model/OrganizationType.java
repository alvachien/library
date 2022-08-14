package com.alvachien.libraryapi.storage.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "org_type_def")
public class OrganizationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id")
    private Long id;

    @Column(name="type_value", nullable = false, columnDefinition = "INT")
    @Convert(converter = OrganizationTypeEnumConverter.class)
    private OrganizationTypeEnum typeValue;

    @Column(name="type_name", nullable = true, length = 50)
    private String typeName;

    @ManyToMany(mappedBy="orgtypes")
    private List<Organization> organizations;

    public OrganizationType() {
        this.organizations = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrganizationTypeEnum getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(OrganizationTypeEnum typeValue) {
        this.typeValue = typeValue;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }
}
