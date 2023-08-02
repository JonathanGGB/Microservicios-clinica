package com.clinic.studies.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "current_sufferings")
@Data
@NoArgsConstructor
public class CurrentSuffering {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "patient_id", nullable = false)
	private Long patientId;
	@Column(name = "date", updatable = false)
	@JsonIgnore
	private Timestamp date;
	@Column(name = "description", length = 255)
	private String description;
	@Column(name = "evolution", length = 255)
	private String evolution;
	@Column(name = "state", length = 50)
	private String state;
	
	@PrePersist
	protected void onCreate() {
		if(date == null) {
			date = new Timestamp(new Date().getTime());
		}
	}
	
}
