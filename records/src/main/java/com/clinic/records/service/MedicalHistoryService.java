package com.clinic.records.service;

import java.util.List;

import com.clinic.records.entity.MedicalHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinic.records.error.RecordsException;
import com.clinic.records.repository.MedicalHistoryRepository;
import lombok.extern.log4j.Log4j2;
import java.util.Optional;

@Service
@Log4j2
public class MedicalHistoryService {
    @Autowired
    private MedicalHistoryRepository familyRepository;

    public MedicalHistory createFamilyHistory(MedicalHistory medicalHistory) throws Exception {
        Optional<MedicalHistory> familyHistoryExists = familyRepository
                .findByPatientId(medicalHistory.getPatientId());
        if(!familyHistoryExists.isPresent()) {
            log.info("Create MedicalHistory: " + medicalHistory.toString());
        }
        throw new RecordsException("There's an already existing family History for that patient");
    }

    public MedicalHistory updateFamilyHistory(MedicalHistory medicalHistory)  throws Exception {
        if(medicalHistory.getId() == null) {
            throw new RecordsException("This family history cannot be updated");
        }
        Optional<MedicalHistory> familyHistoryExists = familyRepository
                .findByIdAndPatientId(medicalHistory.getId(), medicalHistory.getPatientId());
        if(familyHistoryExists.isPresent()) {
            log.info("Update Family History: "+ medicalHistory.toString());
            return familyRepository.save(medicalHistory);
        }
        throw new RecordsException("This patient's family history does not exists");
    }

    public List<MedicalHistory> getAllFamilyHistories() throws Exception {
        List<MedicalHistory> histories = familyRepository.findAll();
        if(histories.isEmpty()) {
            throw new RecordsException("No histories found");
        }
        return histories;
    }

    public void deleteFamilyHistory(Long id) throws Exception {
        Optional<MedicalHistory> historyExists = familyRepository.findById(id);
        if(!historyExists.isPresent()) {
            throw new RecordsException("No history found for deletion");
        }
        familyRepository.deleteById(id);
    }
}
