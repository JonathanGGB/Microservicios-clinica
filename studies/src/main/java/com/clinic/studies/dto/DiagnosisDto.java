package com.clinic.studies.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class DiagnosisDto {
    private String diagnosis;
    private Timestamp diagnosisDate;
}
