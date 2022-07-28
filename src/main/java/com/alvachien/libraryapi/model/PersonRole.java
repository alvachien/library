package com.alvachien.libraryapi.model;

public enum PersonRole {
    AUTHOR( 'A' ),
    ACTOR( 'B' ),
    DIRECTOR('C');
 
    private final char code;
 
    PersonRole(char code) {
        this.code = code;
    }
 
    public static PersonRole fromCode(char code) {
        if ( code == 'A' || code == 'a' ) {
            return AUTHOR;
        }
        if ( code == 'B' || code == 'b' ) {
            return ACTOR;
        }
        if ( code == 'C' || code == 'c' ) {
            return DIRECTOR;
        }
        
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported!"
        );
    }
 
    public char getCode() {
        return code;
    }
}
