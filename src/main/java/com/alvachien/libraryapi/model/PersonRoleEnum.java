package com.alvachien.libraryapi.model;

public enum PersonRoleEnum {
    AUTHOR( 'A' ),
    ACTOR( 'B' ),
    DIRECTOR('C'),
    OWNDEFINED('Z');
 
    private final char code;
 
    PersonRoleEnum(char code) {
        this.code = code;
    }
 
    public static PersonRoleEnum fromCode(char code) {
        if ( code == 'A' || code == 'a' ) {
            return AUTHOR;
        }
        if ( code == 'B' || code == 'b' ) {
            return ACTOR;
        }
        if ( code == 'C' || code == 'c' ) {
            return DIRECTOR;
        }
        if ( code == 'Z' || code == 'z' ) {
            return OWNDEFINED;
        }
        
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported!"
        );
    }
 
    public char getCode() {
        return code;
    }
}
