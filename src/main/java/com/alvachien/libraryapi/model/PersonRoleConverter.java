package com.alvachien.libraryapi.model;

import javax.persistence.AttributeConverter;

public class PersonRoleConverter implements AttributeConverter<PersonRole, Character> {
 
    @Override
    public Character convertToDatabaseColumn(PersonRole role) {
        if ( role == null ) {
            return null;
        }
 
        return role.getCode();
    }
 
    @Override
    public PersonRole convertToEntityAttribute(Character value) {
        if ( value == null ) {
            return null;
        }
 
        return PersonRole.fromCode( value );
    }
}

