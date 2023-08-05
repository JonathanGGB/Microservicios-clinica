package com.clinic.studies.service;

import com.clinic.studies.dto.CompleteMedicalHistoryDto;
import com.clinic.studies.entity.MedicalHistory;
import com.clinic.studies.error.StudiesException;
import com.clinic.studies.repository.MedicalHistoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class CompleteMedicalHistoryService {
    @Autowired
    HumanSystemService humanSystemService;
    @Autowired
    private MedicalHistoryService medicalHistoryService;
    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;
    @Autowired
    DiagnosisService diagnosisService;

    public CompleteMedicalHistoryDto getCompleteMedicalHistoryById(Long patientId, Long medicalHistoryId) throws Exception{
        Optional<MedicalHistory> medicalHistoryExists = medicalHistoryRepository.findByIdAndPatientId(medicalHistoryId, patientId);
        if(!medicalHistoryExists.isPresent()) {
            throw new StudiesException("No medical history for this patient");
        }
        MedicalHistory medicalHistory = medicalHistoryExists.get();
        CompleteMedicalHistoryDto completeMedicalHistoryDto = new CompleteMedicalHistoryDto();
        completeMedicalHistoryDto.setMedicalHistoryDto(medicalHistoryService.getMedicalHistoryDto(medicalHistory.getPatientId(), medicalHistory.getId()));
        completeMedicalHistoryDto.setDiagnosisDto(diagnosisService.getDiagnosisDto(medicalHistory.getDiagnosisId()));
        completeMedicalHistoryDto.setHumanSystemDto(humanSystemService.getHumanSystemDtoAndDiseasesAndReassessment(diagnosisService.getDiagnosisById(medicalHistory.getDiagnosisId()).getHumanSystemId()));
        return completeMedicalHistoryDto;
    }
}
