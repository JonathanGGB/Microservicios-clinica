package com.clinic.records.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientDto {
	private String fullName;
	private int age;
	private String address;
	private String cellphoneNum;
	private String email;
	private String sex;
	private String maritalStatus;
	private String schooling;
	private String occupation;
}
