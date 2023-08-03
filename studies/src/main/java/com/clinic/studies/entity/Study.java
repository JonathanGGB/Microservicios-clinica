package com.clinic.studies.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "studies")
@Data
@NoArgsConstructor
public class Study {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "patient_id")
    private Long patientId;
    @Column(name = "file_path", length = 250)
    private String filePath;
    @Column(name = "file_type", length = 50)
    private String fileType;
}
