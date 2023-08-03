package com.clinic.records.service;

import com.clinic.records.entity.PathologicalPersonalHistory;
import com.clinic.records.entity.Patient;
import com.clinic.records.error.RecordsException;
import com.clinic.records.repository.PathologicalPersonalHistoryRepository;
import com.clinic.records.repository.PatientRepository;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PathologicalPersonalHistoryService {
    @Autowired
    private PathologicalPersonalHistoryRepository pathologicalPersonalHistoryRepository;
    @Autowired
    private PatientRepository patientRepository;

    public List<PathologicalPersonalHistory> getAllPathologicalHistories() throws RecordsException {
        List<PathologicalPersonalHistory> pathologicalPersonalHistories = pathologicalPersonalHistoryRepository.findAll();
        if(pathologicalPersonalHistories.isEmpty()){
            throw new RecordsException("No Pathological Personal Histories found");
        }
        return pathologicalPersonalHistories;
    }

    public PathologicalPersonalHistory createPathologicalHistory(PathologicalPersonalHistory pathologicalPersonalHistory) throws RecordsException {
        Optional<Patient> patientExists = patientRepository.findById(pathologicalPersonalHistory.getPatientId());
        Optional<PathologicalPersonalHistory> pathologicalPersonalHistoryExists = pathologicalPersonalHistoryRepository
        		.findByPatientId(pathologicalPersonalHistory.getPatientId());
        
        if((patientExists.isPresent()) && (!pathologicalPersonalHistoryExists.isPresent()) ){
            log.info("Created Pathological Personal History: "+pathologicalPersonalHistory.toString());
            return pathologicalPersonalHistoryRepository.save(pathologicalPersonalHistory);
        }
        throw new RecordsException("There's already an existing pathological personal history for this patient");
    }

    public PathologicalPersonalHistory updatePathologicalHistory(PathologicalPersonalHistory pathologicalPersonalHistory) throws RecordsException {
        if (pathologicalPersonalHistory.getId() == null){
            throw new RecordsException("This pathological personal history can't be updated.");
        }
        Optional<PathologicalPersonalHistory> pathologicalPersonalHistoryExist = pathologicalPersonalHistoryRepository.findById(pathologicalPersonalHistory.getId());
        if (pathologicalPersonalHistoryExist.isPresent()){
            log.info("Updated pathological personal history "+ pathologicalPersonalHistory.toString());
            pathologicalPersonalHistoryRepository.save(pathologicalPersonalHistory);
        }
        throw new RecordsException("This pathological personal history doesn't exist.");
    }

    public void deletePathologicalHistory(Long id) throws RecordsException {
        Optional<PathologicalPersonalHistory> pathologicalPersonalHistoryExist = pathologicalPersonalHistoryRepository.findById(id);
        if(!pathologicalPersonalHistoryExist.isPresent()){
            throw new RecordsException("No non-pathological personal history found for deletion");
        }
        pathologicalPersonalHistoryRepository.deleteById(id);
    }
}
