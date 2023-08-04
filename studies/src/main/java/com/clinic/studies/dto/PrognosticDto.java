package com.clinic.studies.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class PrognosticDto {
    private String patientName;
    private String patientDescription;
    private Timestamp prognosticDate;
}
