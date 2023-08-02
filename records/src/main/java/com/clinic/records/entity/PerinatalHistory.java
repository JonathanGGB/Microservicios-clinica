package com.clinic.records.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "perinatal_history")
@Data
@NoArgsConstructor
public class PerinatalHistory {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "patient_id")
	private Long patientId;
	@Column(name = "gestation_weeks", nullable = false)
	private int gestattionWeeks;
	@Column(name = "apgar", length = 255)
	private String apgar;
	@Column(name = "weigh", nullable = false)
	private BigDecimal weigh;
	@Column(name = "heigth", nullable =  false)
	private BigDecimal heigth;
	@Column(name = "prenancy_num", nullable =  false)
	private int pregnancyNum;
	@Column(name = "birth_problems")
	private String birthProblems;
	
}
