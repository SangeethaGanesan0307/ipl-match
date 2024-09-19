package com.indium.spring_boot_assessment.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "powerplay_info")
public class PowerPlayInfo  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "powerplay_id")
    private int powerplayId;

    @ManyToOne
    @JoinColumn(name = "inning_id", referencedColumnName = "inning_id")
    private InningsInfo inningsInfo;

    @Column(name = "from_over")
    private double fromOver;

    @Column(name = "to_over")
    private double toOver;

    @Column(name = "powerplay_type")
    private String powerplayType;

    // Getters and Setters

    public int getPowerplayId() {
        return powerplayId;
    }

    public void setPowerplayId(int powerplayId) {
        this.powerplayId = powerplayId;
    }

    public InningsInfo getInningsInfo() {
        return inningsInfo;
    }

    public void setInningsInfo(InningsInfo inningsInfo) {
        this.inningsInfo = inningsInfo;
    }

    public double getFromOver() {
        return fromOver;
    }

    public void setFromOver(double fromOver) {
        this.fromOver = fromOver;
    }

    public double getToOver() {
        return toOver;
    }

    public void setToOver(double toOver) {
        this.toOver = toOver;
    }

    public String getPowerplayType() {
        return powerplayType;
    }

    public void setPowerplayType(String powerplayType) {
        this.powerplayType = powerplayType;
    }

    public void setInningId(int inningId) {
    }
}