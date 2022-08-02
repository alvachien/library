package com.alvachien.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alvachien.libraryapi.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {   
}
