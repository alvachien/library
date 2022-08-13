package com.alvachien.libraryapi.storage.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;

@Entity
@EdmIgnore
public class RootEntity {
    @Id
    private Integer id;
}
