package com.clinic.studies.service;

import com.clinic.studies.dto.MedicalHistoryDto;
import com.clinic.studies.entity.MedicalHistory;
import com.clinic.studies.error.StudiesException;
import com.clinic.studies.repository.MedicalHistoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class MedicalHistoryService {
    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistory createmedicalHistory(MedicalHistory medicalHistory) throws Exception {
        Optional<MedicalHistory> medicalHistoryExists = medicalHistoryRepository
                .findByPatientId(medicalHistory.getPatientId());
        if (!medicalHistoryExists.isPresent()) {
            log.info("Create MedicalHistory: " + medicalHistory.toString());
            return medicalHistoryRepository.save(medicalHistory);
        }
        throw new StudiesException("There's an already existing medical History for that patient");
    }

    public MedicalHistory updatemedicalHistory(MedicalHistory medicalHistory) throws Exception {
        if (medicalHistory.getId() == null) {
            throw new StudiesException("This medical history cannot be updated");
        }
        Optional<MedicalHistory> medicalHistoryExists = medicalHistoryRepository
                .findByIdAndPatientId(medicalHistory.getId(), medicalHistory.getPatientId());
        if (medicalHistoryExists.isPresent()) {
            log.info("Update medical History: " + medicalHistory.toString());
            return medicalHistoryRepository.save(medicalHistory);
        }
        throw new StudiesException("This patient's medical history does not exists");
    }

    public List<MedicalHistory> getAllMedicalHistories() throws Exception {
        List<MedicalHistory> histories = medicalHistoryRepository.findAll();
        if (histories.isEmpty()) {
            throw new StudiesException("No histories found");
        }
        return histories;
    }

    public MedicalHistoryDto getMedicalHistoryDto(Long patientId, Long medicalHistoryId) throws Exception {
        Optional<MedicalHistory> medicalHistoryExists = medicalHistoryRepository.findByIdAndPatientId(medicalHistoryId, patientId);
        if (!medicalHistoryExists.isPresent()) {
            throw new StudiesException(("No medical history found for this patient"));
        }
        MedicalHistory medicalHistory = medicalHistoryExists.get();
        MedicalHistoryDto medicalHistoryDto = new MedicalHistoryDto();
        medicalHistoryDto.setDate(medicalHistory.getDate());
        medicalHistoryDto.setTreatments(medicalHistory.getTreatments());
        return medicalHistoryDto;
    }

    public void deleteMedicalHistory(Long id) throws Exception {
        Optional<MedicalHistory> historyExists = medicalHistoryRepository.findById(id);
        if(!historyExists.isPresent()) {
            throw new StudiesException("No history found for deletion");
        }
        medicalHistoryRepository.deleteById(id);
    }

}



