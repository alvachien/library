package com.alvachien.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alvachien.libraryapi.model.PersonRole;

public interface PersonRoleRepository extends JpaRepository<PersonRole, Long> {
    
}
