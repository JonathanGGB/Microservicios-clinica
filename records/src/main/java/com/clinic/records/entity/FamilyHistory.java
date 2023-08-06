package com.clinic.records.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="familiy_history")
@Data
@NoArgsConstructor
public class FamilyHistory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "heart_diseases", nullable = false)
    private boolean heartDiseases;
    @Column(name = "allergies", nullable = false)
    private boolean allergies;
    @Column(name = "diabetes", nullable = false)
    private boolean diabetes;
    @Column(name = "psychiatrics", nullable = false)
    private boolean psychiatrics;
    @Column(name = "other_syndromes")
    private boolean otherSyndromes;
    @Column(name = "patient_id", nullable = false)
    private Long patientId;


}
