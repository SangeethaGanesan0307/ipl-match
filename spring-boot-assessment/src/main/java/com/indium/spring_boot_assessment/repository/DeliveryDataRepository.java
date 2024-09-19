package com.indium.spring_boot_assessment.repository;

import com.indium.spring_boot_assessment.entites.DeliveryData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DeliveryDataRepository extends JpaRepository<DeliveryData, Long> {

    @Query("SELECT SUM(d.runsScoredByBatter) FROM DeliveryData d WHERE d.batterName = :playerName")
    Long getCumulativeScoreByPlayerName(@Param("playerName") String playerName);



    @Query("SELECT d.totalRunsScored " +
            "FROM DeliveryData d " +
            "JOIN d.overData o " +
            "JOIN o.inningsInfo i " +
            "JOIN i.matchDetails m " +
            "WHERE m.matchDate = :matchDate")
    List<Integer> getScoresByDate(@Param("matchDate") LocalDate matchDate);

    @Query("SELECT tp.fullName AS batsman, SUM(dd.totalRunsScored) AS totalRuns " +
            "FROM DeliveryData dd " +
            "JOIN TeamPlayer tp ON dd.batterName = tp.fullName " +
            "GROUP BY tp.fullName " +
            "ORDER BY totalRuns ASC")
    Page<Object[]> findTopBatsmen(Pageable pageable);
}









