package com.clinic.admin.entity;

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
@Table(name = "appointment")
@Data
@NoArgsConstructor
public class Appointment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "patient_id")
    private Long patientId;
    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd")
    private Timestamp date;
    @Column(name = "status", length = 50)
    private String status;
    @Column(name = "patient_type", length = 50)
    private String patientType;
    @Column(name = "session_number")
    private int sessionNumber;
    @Column(name = "costs", columnDefinition="Decimal(10,2) default '0.00'")
    private Double costs;
}
