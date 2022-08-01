package com.alvachien.libraryapi.model;

import javax.persistence.AttributeConverter;

public class OrganizationTypeEnumConverter implements AttributeConverter<OrganizationTypeEnum, Character> {
 
    @Override
    public Character convertToDatabaseColumn(OrganizationTypeEnum role) {
        if ( role == null ) {
            return null;
        }
 
        return role.getCode();
    }
 
    @Override
    public OrganizationTypeEnum convertToEntityAttribute(Character value) {
        if ( value == null ) {
            return null;
        }
 
        return OrganizationTypeEnum.fromCode( value );
    }
    
}
