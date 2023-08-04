package com.clinic.studies.dto;

import com.clinic.studies.entity.SystemDisease;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HumanSystemDto {
    private String humanSystemName;
    private List<SystemDiseaseDto> diseases;
}
