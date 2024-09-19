package com.indium.spring_boot_assessment.entites;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "match_metadata")
public class Match_metadata  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "metadata_id")
    private int metadataId;

    @Column(name = "version")
    private String version;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "revision_number")
    private int revisionNumber;

    // Getters and Setters

    public int getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(int metadataId) {
        this.metadataId = metadataId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public int getRevisionNumber() {
        return revisionNumber;
    }

    public void setRevisionNumber(int revisionNumber) {
        this.revisionNumber = revisionNumber;
    }
}
