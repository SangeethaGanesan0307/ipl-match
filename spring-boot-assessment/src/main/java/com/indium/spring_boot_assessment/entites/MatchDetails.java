package com.indium.spring_boot_assessment.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "match_details")
public class MatchDetails  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private int matchId;

    @ManyToOne
    @JoinColumn(name = "metadata_id", referencedColumnName = "metadata_id")
    private Match_metadata matchMetadata;

    @Column(name = "balls_per_over")
    private int ballsPerOver;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "match_date")
    private LocalDate matchDate;

    @Column(name = "match_num")
    private int matchNum;

    @Column(name = "event_title")
    private String eventTitle;

    @Column(name = "gender_type")
    private String genderType;

    @Column(name = "format_type")
    private String formatType;

    @Column(name = "winning_team")
    private String winningTeam;

    @Column(name = "margin_of_victory_runs")
    private int marginOfVictoryRuns;

    @Column(name = "total_overs")
    private int totalOvers;

    @Column(name = "best_player")
    private String bestPlayer;

    @Column(name = "team_category")
    private String teamCategory;

    @Column(name = "toss_winning_team")
    private String tossWinningTeam;

    @Column(name = "toss_decision")
    private String tossDecision;

    @Column(name = "stadium_name")
    private String stadiumName;

    @OneToMany(mappedBy = "matchDetails")
    private List<InningsInfo> inningsInfo;

    // Getters and Setters

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public Match_metadata getMatchMetadata() {
        return matchMetadata;
    }

    public void setMatchMetadata(Match_metadata matchMetadata) {
        this.matchMetadata = matchMetadata;
    }

    public int getBallsPerOver() {
        return ballsPerOver;
    }

    public void setBallsPerOver(int ballsPerOver) {
        this.ballsPerOver = ballsPerOver;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public int getMatchNum() {
        return matchNum;
    }

    public void setMatchNum(int matchNum) {
        this.matchNum = matchNum;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getGenderType() {
        return genderType;
    }

    public void setGenderType(String genderType) {
        this.genderType = genderType;
    }

    public String getFormatType() {
        return formatType;
    }

    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    public String getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(String winningTeam) {
        this.winningTeam = winningTeam;
    }

    public int getMarginOfVictoryRuns() {
        return marginOfVictoryRuns;
    }

    public void setMarginOfVictoryRuns(int marginOfVictoryRuns) {
        this.marginOfVictoryRuns = marginOfVictoryRuns;
    }

    public int getTotalOvers() {
        return totalOvers;
    }

    public void setTotalOvers(int totalOvers) {
        this.totalOvers = totalOvers;
    }

    public String getBestPlayer() {
        return bestPlayer;
    }

    public void setBestPlayer(String bestPlayer) {
        this.bestPlayer = bestPlayer;
    }

    public String getTeamCategory() {
        return teamCategory;
    }

    public void setTeamCategory(String teamCategory) {
        this.teamCategory = teamCategory;
    }

    public String getTossWinningTeam() {
        return tossWinningTeam;
    }

    public void setTossWinningTeam(String tossWinningTeam) {
        this.tossWinningTeam = tossWinningTeam;
    }

    public String getTossDecision() {
        return tossDecision;
    }

    public void setTossDecision(String tossDecision) {
        this.tossDecision = tossDecision;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }
}
