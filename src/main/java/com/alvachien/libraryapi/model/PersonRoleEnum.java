package com.alvachien.libraryapi.model;

public enum PersonRoleEnum {
    AUTHOR( 1 ),
    ACTOR( 2 ),
    DIRECTOR(3),
    OWNDEFINED(-1);
 
    private final int code;
 
    PersonRoleEnum(int code) {
        this.code = code;
    }
 
    public static PersonRoleEnum fromCode(int code) {
        if ( code == 1 ) {
            return AUTHOR;
        }
        if ( code == 2 ) {
            return ACTOR;
        }
        if ( code == 3 ) {
            return DIRECTOR;
        }
        if ( code == -1) {
            return OWNDEFINED;
        }
        
        throw new UnsupportedOperationException(
            "The code " + code + " is not supported!"
        );
    }
 
    public int getCode() {
        return code;
    }
}
