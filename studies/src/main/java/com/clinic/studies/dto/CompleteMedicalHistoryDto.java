package com.clinic.studies.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompleteMedicalHistoryDto {
    DiagnosisDto diagnosisDto;
    HumanSystemDto humanSystemDto;
    MedicalHistoryDto medicalHistoryDto;
}
