package com.indium.spring_boot_assessment.repository;


import com.indium.spring_boot_assessment.entites.Match_metadata;
import org.springframework.data.repository.CrudRepository;

public interface Match_metadataRepository extends CrudRepository<Match_metadata, Integer> {
    // Custom query methods can be defined here if needed
}

