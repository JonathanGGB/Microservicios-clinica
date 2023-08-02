package com.clinic.records.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "non_pathological_personal_history")
@Data
@NoArgsConstructor
public class NonPathologicalPersonalHistory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "personal_habits", length = 255)
    private String personalHabits;
    @Column(name="bathroom", length = 255)
    private String bathroom;
    @Column(name = "room", length = 255)
    private String room;
    @Column(name = "smoking", length = 255)
    private String smoking;
    @Column(name = "alcoholism", length = 255)
    private String alcoholism;
    @Column(name = "vaccines", length = 255)
    private String vaccines;
    @Column(name = "physical_activity", length = 255)
    private String physicalActivity;
    @Column(name = "feeding", length = 255)
    private String feeding;
    @Column(name = "civil_status", length = 255)
    private String civilStatus;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;





}
