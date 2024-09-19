package com.indium.spring_boot_assessment.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "delivery_data")
public class DeliveryData  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private int deliveryId;

    @ManyToOne
    @JoinColumn(name = "over_id", referencedColumnName = "over_id")
    private OverData overData;

    @Column(name = "batter_name")
    private String batterName;

    @Column(name = "bowler_name")
    private String bowlerName;

    @Column(name = "non_striker_name")
    private String nonStrikerName;

    @Column(name = "runs_scored_by_batter")
    private int runsScoredByBatter;

    @Column(name = "runs_scored_from_extras")
    private int runsScoredFromExtras;

    @Column(name = "total_runs_scored")
    private int totalRunsScored;

    @Column(name = "type_of_extra")
    private int typeOfExtra;

    @Column(name = "wicket_mode")
    private String wicketMode;

    @Column(name = "dismissed_player")
    private String dismissedPlayer;

    // Getters and Setters

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public OverData getOverData() {
        return overData;
    }

    public void setOverData(OverData overData) {
        this.overData = overData;
    }

    public String getBatterName() {
        return batterName;
    }

    public void setBatterName(String batterName) {
        this.batterName = batterName;
    }

    public String getBowlerName() {
        return bowlerName;
    }

    public void setBowlerName(String bowlerName) {
        this.bowlerName = bowlerName;
    }

    public String getNonStrikerName() {
        return nonStrikerName;
    }

    public void setNonStrikerName(String nonStrikerName) {
        this.nonStrikerName = nonStrikerName;
    }

    public int getRunsScoredByBatter() {
        return runsScoredByBatter;
    }

    public void setRunsScoredByBatter(int runsScoredByBatter) {
        this.runsScoredByBatter = runsScoredByBatter;
    }

    public int getRunsScoredFromExtras() {
        return runsScoredFromExtras;
    }

    public void setRunsScoredFromExtras(int runsScoredFromExtras) {
        this.runsScoredFromExtras = runsScoredFromExtras;
    }

    public int getTotalRunsScored() {
        return totalRunsScored;
    }

    public void setTotalRunsScored(int totalRunsScored) {
        this.totalRunsScored = totalRunsScored;
    }

    public int getTypeOfExtra() {
        return typeOfExtra;
    }

    public int setTypeOfExtra(int typeOfExtra) {
        this.typeOfExtra = typeOfExtra;
        return typeOfExtra;
    }

    public String getWicketMode() {
        return wicketMode;
    }

    public void setWicketMode(String wicketMode) {
        this.wicketMode = wicketMode;
    }

    public String getDismissedPlayer() {
        return dismissedPlayer;
    }

    public void setDismissedPlayer(String dismissedPlayer) {
        this.dismissedPlayer = dismissedPlayer;
    }
}
