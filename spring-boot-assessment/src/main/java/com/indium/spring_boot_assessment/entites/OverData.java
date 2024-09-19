package com.indium.spring_boot_assessment.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "over_data")
public class OverData  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "over_id")
    private int overId;

    @ManyToOne
    @JoinColumn(name = "inning_id", referencedColumnName = "inning_id")
    private InningsInfo inningsInfo;

    @Column(name = "over_sequence")
    private int overSequence;

    // Getters and Setters

    public int getOverId() {
        return overId;
    }

    public void setOverId(int overId) {
        this.overId = overId;
    }

    public InningsInfo getInningsInfo() {
        return inningsInfo;
    }

    public void setInningsInfo(InningsInfo inningsInfo) {
        this.inningsInfo = inningsInfo;
    }

    public int getOverSequence() {
        return overSequence;
    }

    public void setOverSequence(int overSequence) {
        this.overSequence = overSequence;
    }
}
