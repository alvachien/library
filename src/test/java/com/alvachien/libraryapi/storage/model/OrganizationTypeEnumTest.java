package com.alvachien.libraryapi.storage.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Organization Type")
public class OrganizationTypeEnumTest {
    @Test
    public void createPublishingHouse() {
        OrganizationTypeEnum bce = OrganizationTypeEnum.PUBLISHHOUSE;
        int code = bce.getCode();
        Assertions.assertEquals(code, 1);
    }

    @Test
    public void createOwnDefined() {
        OrganizationTypeEnum bce = OrganizationTypeEnum.OWNDEFINED;
        int code = bce.getCode();
        Assertions.assertEquals(code, 0);
    }

    @Test
    public void parsePublishingHouse() {
        int codeValue = 1;
        OrganizationTypeEnumConverter converter = new OrganizationTypeEnumConverter();
        OrganizationTypeEnum bce = converter.convertToEntityAttribute(codeValue);
        Assertions.assertEquals(OrganizationTypeEnum.PUBLISHHOUSE, bce);
    }    

    @Test
    public void parseOWNDEFINED() {
        int codeValue = 0;
        OrganizationTypeEnumConverter converter = new OrganizationTypeEnumConverter();
        OrganizationTypeEnum bce = converter.convertToEntityAttribute(codeValue);
        Assertions.assertEquals(OrganizationTypeEnum.OWNDEFINED, bce);
    }    
}
