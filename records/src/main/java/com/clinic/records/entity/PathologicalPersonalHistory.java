package com.clinic.records.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pathological_personal_history")
@Data
@NoArgsConstructor
public class PathologicalPersonalHistory {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "surgeries", length = 255)
    private String surgeries;
    @Column(name = "addictions", length = 255)
    private String addictions;
    @Column(name = "std", length = 255)
    private  String std;
    @Column(name = "allergies", length = 255)
    private String allergies;
    @Column(name = "join_aliments", length = 255)
    private String jointAliments;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
}
