package com.indium.spring_boot_assessment.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "team_players")
public class TeamPlayer  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private int playerId;

    @ManyToOne
    @JoinColumn(name = "team_id")//, referencedColumnName = "team_id")
    private MatchTeams matchTeams;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "unique_code")
    private String uniqueCode;

    // Getters and Setters

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public MatchTeams getMatchTeams() {
        return matchTeams;
    }

    public void setMatchTeams(MatchTeams matchTeams) {
        this.matchTeams = matchTeams;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
}