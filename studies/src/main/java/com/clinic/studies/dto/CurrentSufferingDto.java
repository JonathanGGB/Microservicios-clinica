package com.clinic.studies.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class CurrentSufferingDto {
    private String patientName;
    private Timestamp date;
    private String description;
    private String evolution;
    private String state;
}
