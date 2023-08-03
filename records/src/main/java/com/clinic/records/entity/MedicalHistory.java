package com.clinic.records.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Entity
@Table(name = "medical_history")
@Data
@NoArgsConstructor
public class MedicalHistory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "patient_id")
    private Long patientId;
    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd")
    private Timestamp date;
    @Column(name = "diagnosis_id")
    private Long diagnosisId;
    @Column(name = "treatments", length = 500)
    private String treatments;
}
