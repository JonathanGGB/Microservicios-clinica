package com.clinic.studies.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "physical_exploration")
@Data
@NoArgsConstructor
public class PhysicalExploration {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "patient_id", nullable = false)
	private Long patientId;
	@Column(name = "exploration_description", length = 500)
	private String explorationDescription;
	@Column(name = "exploration_date", updatable = false)
	@JsonIgnore
	private Timestamp explorationDate;
	
	@PrePersist
	protected void onCreate() {
		if(explorationDate == null) {
			explorationDate = new Timestamp(new Date().getTime());
		}
	}
}
