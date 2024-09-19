package com.indium.spring_boot_assessment.repository;

import com.indium.spring_boot_assessment.entites.MatchOfficials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchOfficialsRepository extends CrudRepository<MatchOfficials, Integer> {
    // Custom query methods can be defined here if needed
}
