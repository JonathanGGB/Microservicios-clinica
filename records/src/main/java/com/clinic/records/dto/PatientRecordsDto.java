package com.clinic.records.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientRecordsDto {
	private PatientDto patientDto;
	private FamilyHistoryDto familyHistoryDto;
	private PathologicalPersonalHistoryDto pathologicalPersonalHistoryDto;
	private NonPathPersHistoryDto nonPathPersHistoryDto;
}
