package com.alvachien.libraryapi.model;


public enum BookCategoryEnum {
    NOVEL( 'N' ),
    OWNDEFINED('Z');
 
    private final char code;
 
    BookCategoryEnum(char code) {
        this.code = code;
    }
 
    public static BookCategoryEnum fromCode(char code) {
        if ( code == 'N' || code == 'n' ) {
            return NOVEL;
        }
        
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported!"
        );
    }
 
    public char getCode() {
        return code;
    }
    
}
