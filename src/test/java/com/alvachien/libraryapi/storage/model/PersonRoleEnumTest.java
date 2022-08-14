package com.alvachien.libraryapi.storage.model;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Person Role")
public class PersonRoleEnumTest {
    @Test
    public void createAuthor() {
        PersonRoleEnum bce = PersonRoleEnum.AUTHOR;
        int code = bce.getCode();
        Assertions.assertEquals(code, 1);
    }

    @Test
    public void createActor() {
        PersonRoleEnum bce = PersonRoleEnum.ACTOR;
        int code = bce.getCode();
        Assertions.assertEquals(code, 2);
    }

    @Test
    public void createDirector() {
        PersonRoleEnum bce = PersonRoleEnum.DIRECTOR;
        int code = bce.getCode();
        Assertions.assertEquals(code, 3);
    }

    @Test
    public void createOwnDefined() {
        PersonRoleEnum bce = PersonRoleEnum.OWNDEFINED;
        int code = bce.getCode();
        Assertions.assertEquals(code, 0);
    }

    @Test
    public void parseAuthor() {
        int codeValue = 1;
        PersonRoleEnumConverter converter = new PersonRoleEnumConverter();
        PersonRoleEnum bce = converter.convertToEntityAttribute(codeValue);
        Assertions.assertEquals(PersonRoleEnum.AUTHOR, bce);
    }

    @Test
    public void parseActor() {
        int codeValue = 2;
        PersonRoleEnumConverter converter = new PersonRoleEnumConverter();
        PersonRoleEnum bce = converter.convertToEntityAttribute(codeValue);
        Assertions.assertEquals(PersonRoleEnum.ACTOR, bce);
    }

    @Test
    public void parseDirect() {
        int codeValue = 3;
        PersonRoleEnumConverter converter = new PersonRoleEnumConverter();
        PersonRoleEnum bce = converter.convertToEntityAttribute(codeValue);
        Assertions.assertEquals(PersonRoleEnum.DIRECTOR, bce);
    }

    @Test
    public void parseOWNDEFINED() {
        int codeValue = 0;
        PersonRoleEnumConverter converter = new PersonRoleEnumConverter();
        PersonRoleEnum bce = converter.convertToEntityAttribute(codeValue);
        Assertions.assertEquals(PersonRoleEnum.OWNDEFINED, bce);
    }
}

