package com.indium.spring_boot_assessment.repository;

import com.indium.spring_boot_assessment.entites.MatchDetails;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MatchDetailsRepository extends CrudRepository<MatchDetails, Integer> {
    @Query(value = "SELECT md.match_id, " +
            "       md.city_name, " +
            "       md.match_date, " +
            "       md.match_num, " +
            "       md.event_title, " +
            "       md.gender_type, " +
            "       md.format_type, " +
            "       md.winning_team, " +
            "       md.margin_of_victory_runs, " +
            "       md.total_overs, " +
            "       md.best_player, " +
            "       md.team_category, " +
            "       md.toss_winning_team, " +
            "       md.toss_decision, " +
            "       md.stadium_name " +
            "FROM team_players tp " +
            "JOIN match_teams mt ON tp.team_id = mt.team_id " +
            "JOIN match_details md ON mt.match_id = md.match_id " +
            "WHERE tp.full_name = :playerName", nativeQuery = true)
    List<Object[]> findMatchesByPlayerName(@Param("playerName") String playerName);

    }


