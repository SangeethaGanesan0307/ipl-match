package com.indium.spring_boot_assessment.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "innings_info")
public class InningsInfo  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inning_id")
    private int inningId;

    @ManyToOne
    @JoinColumn(name = "match_id", referencedColumnName = "match_id")
    private MatchDetails matchDetails;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    private MatchTeams matchTeams;

    // Getters and Setters

    public int getInningId() {
        return inningId;
    }

    public void setInningId(int inningId) {
        this.inningId = inningId;
    }

    public MatchDetails getMatchDetails() {
        return matchDetails;
    }

    public void setMatchDetails(MatchDetails matchDetails) {
        this.matchDetails = matchDetails;
    }

    public MatchTeams getMatchTeams() {
        return matchTeams;
    }

    public void setMatchTeams(MatchTeams matchTeams) {
        this.matchTeams = matchTeams;
    }
}
