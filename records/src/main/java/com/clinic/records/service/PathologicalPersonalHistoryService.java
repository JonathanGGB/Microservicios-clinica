package com.clinic.records.service;

import com.clinic.records.entity.PathologicalPersonalHistory;
import com.clinic.records.entity.Patient;
import com.clinic.records.error.RecordsException;
import com.clinic.records.repository.PathologicalPersonalHistoryRepository;
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

    public List<PathologicalPersonalHistory> getAllPathologicalHistories() throws RecordsException {
        List<PathologicalPersonalHistory> pathologicalPersonalHistories = pathologicalPersonalHistoryRepository.findAll();
        if(pathologicalPersonalHistories.isEmpty()){
            throw new RecordsException("No Pathological Personal Histories found");
        }
        return pathologicalPersonalHistories;
    }

    public PathologicalPersonalHistory createPathologicalHistory(PathologicalPersonalHistory pathologicalPersonalHistory) throws RecordsException {
        Patient patient = pathologicalPersonalHistory.getPatient();
        Optional<PathologicalPersonalHistory> pathologicalPersonalHistoryExist = pathologicalPersonalHistoryRepository.findByPatientNameAndPatientLastnames(patient.getName(), patient.getLastnames());
        if(!pathologicalPersonalHistoryExist.isPresent()){
            log.info("Created Pathological Personal History: "+pathologicalPersonalHistory.toString());
            return pathologicalPersonalHistoryRepository.save(pathologicalPersonalHistory);
        }
        throw new RecordsException("There's already an existing pathological personal history for this patient");
    }

    public PathologicalPersonalHistory updatePathologicalHistory(PathologicalPersonalHistory pathologicalPersonalHistory) throws RecordsException {
        if (pathologicalPersonalHistory.getPatient() == null){
            throw new RecordsException("This pathological personal history can't be updated.");
        }
        Optional<PathologicalPersonalHistory> pathologicalPersonalHistoryExist = pathologicalPersonalHistoryRepository.findById(pathologicalPersonalHistory.getId());
        if (!pathologicalPersonalHistoryExist.isPresent()){
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
