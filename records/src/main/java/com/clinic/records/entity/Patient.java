package com.clinic.records.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
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
@Table(name = "patients")
@Data
@NoArgsConstructor
public class Patient {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "registration_date", updatable = false)
	@JsonIgnore
	private Timestamp registrationDate;
	@Column(name = "name", length = 50, nullable = false)
	private String name; 
	@Column(name = "lastnames", length = 50, nullable = false)
	private String lastnames;
	@Column(name = "birthday")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd")
	private Timestamp birthday;
	@Column(name = "sex", nullable = false)
	private boolean sex; //true female, false male
	@Column(name = "address", length = 100, nullable = false)
	private String address;
	@Column(name = "cellphone_num", length = 15, nullable = false)
	private String cellphoneNum;
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	@Column(name = "marital_status", length = 15, nullable = false)
	private String maritalStatus;
	@Column(name = "schooling", length = 20, nullable = false)
	private String schooling;
	@Column(name = "occupation", length = 15, nullable = false)
	private String occupation;
	
	@PrePersist
	protected void onCreate() {
		if (registrationDate == null) {
			registrationDate = new Timestamp(new Date().getTime());
		}
	}
	
	public int getAge() {
		LocalDate birthDate = this.birthday.toLocalDateTime().toLocalDate();
		LocalDate currentDate = LocalDate.now(); 
		int age = 0;
		
		if((birthDate != null) && (currentDate != null)) {
			age = Period.between(birthDate, currentDate).getYears();
		}
		
		return age;
	}
}
