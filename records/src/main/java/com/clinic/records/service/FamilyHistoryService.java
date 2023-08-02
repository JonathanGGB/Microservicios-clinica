package com.clinic.records.service;

import com.clinic.records.entity.FamilyHistory;
import com.clinic.records.entity.Patient;
import com.clinic.records.error.RecordsException;
import com.clinic.records.repository.FamilyHistoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class FamilyHistoryService {
    @Autowired
    private FamilyHistoryRepository familyHistoryRepository;

    public List<FamilyHistory> getAllFamilyHistories() throws RecordsException {
        List<FamilyHistory> familyHistories = familyHistoryRepository.findAll();
        if (familyHistories.isEmpty()) {
            throw new RecordsException("No family histories found.");
        }
        return familyHistories;
    }

    public FamilyHistory createFamilyHistory(FamilyHistory familyHistory) throws RecordsException {
        Patient patient = familyHistory.getPatient();
        Optional<FamilyHistory> familyHistoryExist = familyHistoryRepository.findByPatientNameAndPatientLastnames(patient.getName(), patient.getLastnames());
        if(!familyHistoryExist.isPresent()){
            log.info("Created family history " + familyHistory.toString());
            return familyHistoryRepository.save(familyHistory);
        }
        throw new RecordsException("There's already an existing family history for this patient.");
    }

    public FamilyHistory updateFamilyHistory(FamilyHistory familyHistory) throws RecordsException {
        if(familyHistory.getId() == null){
            throw new RecordsException("This family history cannot be updated");
        }
        Optional<FamilyHistory> familyHistoryExist = familyHistoryRepository.findById(familyHistory.getId());
        if (familyHistoryExist.isPresent()){
            log.info("Updated family history: "+ familyHistory.toString());
            return familyHistoryRepository.save(familyHistory);
        }
        throw new RecordsException("This family history does not exists");
    }

    public void deleteFamilyHistory(Long id) throws Exception{
        Optional<FamilyHistory> familyHistoryExist = familyHistoryRepository.findById(id);
        if(!familyHistoryExist.isPresent()){
            throw new RecordsException("No family history found for deletion");
        }
        familyHistoryRepository.deleteById(id);
    }
}
