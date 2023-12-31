package com.clinic.studies.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientStudiesDto {
    private CurrentSufferingDto currentSufferingDto;
    private PhysicalExplorationDto physicalExplorationDto;
    private PrognosticDto prognosticDto;
}
