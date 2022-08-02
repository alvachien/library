package com.alvachien.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alvachien.libraryapi.model.OrganizationType;

public interface OrganizationTypeRepository extends JpaRepository<OrganizationType, Long> {
    
}
