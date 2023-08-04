package com.clinic.studies.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class MedicalHistoryDto {
    private Timestamp date;
    private String treatments;
}
