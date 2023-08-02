package com.clinic.studies.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "reassessments")
@Data
@NoArgsConstructor
public class Reassessment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "reassessment", length = 255, nullable = false)
    private String reassessment;
    @Column(name = "reassessment_date", updatable = false)
    @JsonIgnore
    private Timestamp reassessmentDate;
    @Column(name = "human_system_id", nullable = false)
    private Long humanSystemId;

    @PrePersist
    protected void onCreate() {
        if (reassessmentDate == null) {
            reassessmentDate = new Timestamp(new Date().getTime());
        }
    }
}
