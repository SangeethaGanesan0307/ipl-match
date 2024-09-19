package com.indium.spring_boot_assessment.repository;

import com.indium.spring_boot_assessment.entites.OverData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverDataRepository extends CrudRepository<OverData, Integer> {
    // Custom query methods can be defined here if needed
}