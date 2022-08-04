package com.alvachien.libraryapi.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;

import com.alvachien.libraryapi.model.OrganizationType;
import com.alvachien.libraryapi.model.OrganizationTypeEnum;

@SpringBootTest
//@ActiveProfiles("unittest")
public class OrganizationTypeControllerTest {
    @Autowired
    private OrganizationTypeController controller;
    
    @BeforeEach
    void beforeEach() {        
    }

    @Test
    void createBookShallWork() {
        OrganizationType ctgy = new OrganizationType();
        ctgy.setTypeValue(OrganizationTypeEnum.OWNDEFINED);
        ctgy.setTypeName("Test");

        // Create
        OrganizationType rst = controller.createObject(ctgy);
        assertTrue(rst != null);
        assertTrue(rst.getTypeName().equals(ctgy.getTypeName()));
        assertTrue(rst.getTypeValue().equals(OrganizationTypeEnum.OWNDEFINED));

    }
    
}
