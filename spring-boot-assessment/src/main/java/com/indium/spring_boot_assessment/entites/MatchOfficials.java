package com.indium.spring_boot_assessment.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "match_officials")
public class MatchOfficials  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "official_id")
    private int officialId;

    @ManyToOne
    @JoinColumn(name = "match_id", referencedColumnName = "match_id")
    private MatchDetails matchDetails;

    @Column(name = "head_referee")
    private String headReferee;

    @Column(name = "backup_umpire")
    private String backupUmpire;

    @Column(name = "television_umpire")
    private String televisionUmpire;

    @Column(name = "field_umpire_one")
    private String fieldUmpireOne;

    @Column(name = "field_umpire_two")
    private String fieldUmpireTwo;

    // Getters and Setters

    public int getOfficialId() {
        return officialId;
    }

    public void setOfficialId(int officialId) {
        this.officialId = officialId;
    }

    public MatchDetails getMatchDetails() {
        return matchDetails;
    }

    public void setMatchDetails(MatchDetails matchDetails) {
        this.matchDetails = matchDetails;
    }

    public String getHeadReferee() {
        return headReferee;
    }

    public void setHeadReferee(String headReferee) {
        this.headReferee = headReferee;
    }

    public String getBackupUmpire() {
        return backupUmpire;
    }

    public void setBackupUmpire(String backupUmpire) {
        this.backupUmpire = backupUmpire;
    }

    public String getTelevisionUmpire() {
        return televisionUmpire;
    }

    public void setTelevisionUmpire(String televisionUmpire) {
        this.televisionUmpire = televisionUmpire;
    }

    public String getFieldUmpireOne() {
        return fieldUmpireOne;
    }

    public void setFieldUmpireOne(String fieldUmpireOne) {
        this.fieldUmpireOne = fieldUmpireOne;
    }

    public String getFieldUmpireTwo() {
        return fieldUmpireTwo;
    }

    public void setFieldUmpireTwo(String fieldUmpireTwo) {
        this.fieldUmpireTwo = fieldUmpireTwo;
    }
}
