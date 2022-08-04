package com.alvachien.libraryapi.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;

import com.alvachien.libraryapi.model.PersonRole;
import com.alvachien.libraryapi.model.PersonRoleEnum;

@SpringBootTest
//@ActiveProfiles("unittest")
public class PersonRoleControllerTest {
    @Autowired
    private PersonRoleController controller;
    
    @BeforeEach
    void beforeEach() {        
    }

    @Test
    void createBookShallWork() {
        PersonRole ctgy = new PersonRole();
        ctgy.setRoleValue(PersonRoleEnum.OWNDEFINED);
        ctgy.setRoleName("Test");

        // Create
        PersonRole rst = controller.createPersonRole(ctgy);
        assertTrue(rst != null);
        assertTrue(rst.getRoleName().equals(ctgy.getRoleName()));
        assertTrue(rst.getRoleValue().equals(PersonRoleEnum.OWNDEFINED));

    }
    
}
