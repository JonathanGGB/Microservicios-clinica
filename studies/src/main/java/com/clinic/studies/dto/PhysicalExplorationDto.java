package com.clinic.studies.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class PhysicalExplorationDto {
    private String patientName;
    private String explorationDescription;
    private Timestamp explorationDate;

}
