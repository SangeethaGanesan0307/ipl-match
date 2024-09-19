package com.indium.spring_boot_assessment.repository;

import com.indium.spring_boot_assessment.entites.PowerPlayInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerPlayInfoRepository extends CrudRepository<PowerPlayInfo, Integer> {
    // Custom query methods can be defined here if needed
}
