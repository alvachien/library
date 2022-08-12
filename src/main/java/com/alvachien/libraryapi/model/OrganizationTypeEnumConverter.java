package com.alvachien.libraryapi.model;

import javax.persistence.AttributeConverter;

public class OrganizationTypeEnumConverter implements AttributeConverter<OrganizationTypeEnum, Integer> {
 
    @Override
    public Integer convertToDatabaseColumn(OrganizationTypeEnum role) {
        if ( role == null ) {
            return null;
        }
 
        return role.getCode();
    }
 
    @Override
    public OrganizationTypeEnum convertToEntityAttribute(Integer value) {
        if ( value == null ) {
            return null;
        }
 
        return OrganizationTypeEnum.fromCode( value );
    }
    
}
