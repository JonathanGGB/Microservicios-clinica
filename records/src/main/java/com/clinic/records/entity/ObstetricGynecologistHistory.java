package com.clinic.records.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "obstetric_gynecologist_history")
@Data
@NoArgsConstructor
public class ObstetricGynecologistHistory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "menarche")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp menarche;
    @Column(name = "mensual_cicle", nullable = false)
    private int mensualCicle;
    @Column(name = "basl", nullable = false)
    private int basl;
    @Column(name = "birth_num", nullable = false)
    private int birthNum;
    @Column(name="lpd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private  Timestamp lpd;
    @Column(name = "abortions", nullable = false)
    private int abortions;
    @Column(name = "cesareans", nullable = false)
    private int cesareans;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;




}
