package com.clinic.studies.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "diagnosis")
@Data
@NoArgsConstructor
public class Diagnosis {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "diagnosis", length = 255, nullable = false)
    private String diagnosis;
    @Column(name = "diagnosis_date", updatable = false)
    @JsonIgnore
    private Timestamp diagnosisDate;
    @Column(name = "human_system_id", nullable = false)
    private Long humanSystemId;

    @PrePersist
    protected void onCreate() {
        if (diagnosisDate == null) {
            diagnosisDate = new Timestamp(new Date().getTime());
        }
    }

}
