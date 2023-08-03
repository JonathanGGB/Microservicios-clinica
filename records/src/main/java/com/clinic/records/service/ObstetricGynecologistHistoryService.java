package com.clinic.records.service;

import com.clinic.records.entity.ObstetricGynecologistHistory;
import com.clinic.records.entity.Patient;
import com.clinic.records.error.RecordsException;
import com.clinic.records.repository.ObstetricGynecologistHistoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ObstetricGynecologistHistoryService {
    @Autowired
    private ObstetricGynecologistHistoryRepository obstetricGynecologistHistoryRepository;

    public List<ObstetricGynecologistHistory> getAllObstetricHistory() throws RecordsException {
        List<ObstetricGynecologistHistory> obstetricGynecologistHistories = obstetricGynecologistHistoryRepository.findAll();
        if(obstetricGynecologistHistories.isEmpty()){
            throw new RecordsException("No obstetric-gynecologist histories found.");
        }
        return obstetricGynecologistHistories;
    }

    public ObstetricGynecologistHistory createObstetricHistory(ObstetricGynecologistHistory obstetricGynecologistHistory) throws RecordsException {
        Patient patient = obstetricGynecologistHistory.getPatient();
        Optional<ObstetricGynecologistHistory> obstetricGynecologistHistoryExist = obstetricGynecologistHistoryRepository.findObstetricGynecologistHistoryByPatientNameAndPatientLastnames(patient.getName(), patient.getLastnames());
        if((!obstetricGynecologistHistoryExist.isPresent()) && (patient.isSex())){
            log.info("Created obstetric-gynecologist history: "+ obstetricGynecologistHistory.toString());
            return obstetricGynecologistHistoryRepository.save(obstetricGynecologistHistory);
        }
        throw new RecordsException("There's already an existing obstetric-gynecologist history for this patient or can't create record for a male patient");
    }

    public ObstetricGynecologistHistory updateObstetricHistory(ObstetricGynecologistHistory obstetricGynecologistHistory) throws RecordsException {
        if(obstetricGynecologistHistory.getId() == null){
            throw new RecordsException("This obstetric-gynecologist personal history can't be updated.");
        }
        Optional<ObstetricGynecologistHistory> obstetricGynecologistHistoryExist = obstetricGynecologistHistoryRepository.findById(obstetricGynecologistHistory.getId());
        if(obstetricGynecologistHistoryExist.isPresent()){
           log.info("Updated obstetric-gynecologist personal history: "+obstetricGynecologistHistory.toString());
           return obstetricGynecologistHistoryRepository.save(obstetricGynecologistHistory);
        }
        throw new RecordsException("This obstetric-gynecologist personal history doesn't exist.");
    }

    public void deleteObstetricHistory(Long id) throws RecordsException {
        Optional<ObstetricGynecologistHistory> obstetricGynecologistHistoryExist = obstetricGynecologistHistoryRepository.findById(id);
        if(!obstetricGynecologistHistoryExist.isPresent()){
            throw new RecordsException("This obstetric-gynecologist personal history to delete.");
        }
        obstetricGynecologistHistoryRepository.deleteById(id);
    }





}
