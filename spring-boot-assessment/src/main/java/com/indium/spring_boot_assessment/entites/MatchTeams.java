package com.indium.spring_boot_assessment.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "match_teams")
public class MatchTeams  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int teamId;

    @ManyToOne
    @JoinColumn(name = "match_id", referencedColumnName = "match_id")
    private MatchDetails matchDetails;

    @Column(name = "team_title")
    private String teamTitle;

    // Getters and Setters

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public MatchDetails getMatchDetails() {
        return matchDetails;
    }

    public void setMatchDetails(MatchDetails matchDetails) {
        this.matchDetails = matchDetails;
    }

    public String getTeamTitle() {
        return teamTitle;
    }

    public void setTeamTitle(String teamTitle) {
        this.teamTitle = teamTitle;
    }

    public void setMatchId(int matchId) {

    }
}
