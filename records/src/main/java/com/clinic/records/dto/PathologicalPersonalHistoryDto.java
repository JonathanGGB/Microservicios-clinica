package com.clinic.records.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PathologicalPersonalHistoryDto {
	private String surgeries;
	private String addiction;
	private String trauma;
	private String std;
	private String allergies;
	private String joint_ailments;
}
