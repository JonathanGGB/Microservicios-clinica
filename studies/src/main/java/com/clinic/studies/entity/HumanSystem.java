package com.clinic.studies.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "human_systems")
@Data
@NoArgsConstructor
public class HumanSystem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "human_system_name", length = 30, nullable = false)
    private String humanSystemName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "humanSystem")
    @ToString.Exclude
    @JsonIgnore
    private List<SystemDisease> diseases;
}
