package com.clinic.studies.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "system_diseases")
@Data
@NoArgsConstructor
public class SystemDisease {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "disease_name", length = 100, nullable = false)
    private String diseaseName;

    @ManyToOne
    @JoinColumn(name = "human_system_id", nullable = false)
    private HumanSystem humanSystem;
}
