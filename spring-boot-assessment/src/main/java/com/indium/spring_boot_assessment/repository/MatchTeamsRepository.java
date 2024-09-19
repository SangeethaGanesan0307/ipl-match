package com.indium.spring_boot_assessment.repository;

import com.indium.spring_boot_assessment.entites.MatchTeams;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchTeamsRepository extends CrudRepository<MatchTeams, Integer> {
    MatchTeams findByTeamTitle(String teamTitle);
    // Custom query methods can be defined here if needed
}
