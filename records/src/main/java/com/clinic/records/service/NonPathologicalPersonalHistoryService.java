package com.clinic.records.service;

import com.clinic.records.dto.NonPathPersHistoryDto;
import com.clinic.records.entity.NonPathologicalPersonalHistory;
import com.clinic.records.entity.Patient;
import com.clinic.records.error.RecordsException;
import com.clinic.records.repository.NonPathologicalPersonalHistoryRepository;
import com.clinic.records.repository.PatientRepository;

import lombok.extern.log4j.Log4j2;

import org.bouncycastle.oer.its.ieee1609dot2.basetypes.PublicVerificationKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class NonPathologicalPersonalHistoryService {
    @Autowired
    private NonPathologicalPersonalHistoryRepository nonPathologicalPersonalHistoryRepository;
    @Autowired
    private PatientRepository patientRepository;

    public List<NonPathologicalPersonalHistory> getAllNonPathologicalHistory() throws RecordsException {
        List<NonPathologicalPersonalHistory> nonPathologicalPersonalHistories = nonPathologicalPersonalHistoryRepository.findAll();
        if (nonPathologicalPersonalHistories.isEmpty()){
            throw new RecordsException("No non-pathological personal histories found");
        }
        return nonPathologicalPersonalHistories;
    }
    
    public NonPathPersHistoryDto getNonPathPersHistoryDtoByPatientId(Long patientId) throws RecordsException {
    	NonPathPersHistoryDto historyDto = new NonPathPersHistoryDto();
    	Optional<NonPathologicalPersonalHistory> historyExists = nonPathologicalPersonalHistoryRepository.findByPatientId(patientId);
    	if(!historyExists.isPresent()) {
    		throw new RecordsException("No non-pathological history found for this patient");
    	}
    	NonPathologicalPersonalHistory history = historyExists.get();
    	historyDto.setAlcholism(history.getAlcoholism());
    	historyDto.setBathroom(history.getBathroom());
    	historyDto.setCivilStatus(patientRepository.findById(patientId).get().getMaritalStatus());
    	historyDto.setFeeding(history.getFeeding());
    	historyDto.setPersonalHabits(history.getPersonalHabits());
    	historyDto.setPhysicalActivity(history.getPhysicalActivity());
    	historyDto.setRoom(history.getRoom());
    	historyDto.setSmoking(history.getSmoking());
    	historyDto.setVaccines(history.getVaccines());
    	return historyDto;
    }
    
    public NonPathologicalPersonalHistory createNonPathologicalHistory(NonPathologicalPersonalHistory nonPathologicalPersonalHistory) throws RecordsException {
        Optional<Patient> patientExists = patientRepository.findById(nonPathologicalPersonalHistory.getPatientId());
        Optional<NonPathologicalPersonalHistory> nonPathologicalPersonalHistoryExist = nonPathologicalPersonalHistoryRepository
        		.findByPatientId(nonPathologicalPersonalHistory.getPatientId());
        if((patientExists.isPresent())&&(!nonPathologicalPersonalHistoryExist.isPresent())){
            log.info("Created non-pathological personal history "+ nonPathologicalPersonalHistory.toString());
            return nonPathologicalPersonalHistoryRepository.save(nonPathologicalPersonalHistory);
        }
        throw new RecordsException("There's already an existing non-pathological personal history for this patient");
    }

    public NonPathologicalPersonalHistory updateNonPathologicalHistory(NonPathologicalPersonalHistory nonPathologicalPersonalHistory) throws RecordsException {
        if(nonPathologicalPersonalHistory.getId() == null){
            throw new RecordsException("This non-pathological personal history can't be updated.");
        }
        Optional<NonPathologicalPersonalHistory> nonPathologicalPersonalHistoryExist = nonPathologicalPersonalHistoryRepository.findById(nonPathologicalPersonalHistory.getId());
        if(nonPathologicalPersonalHistoryExist.isPresent()){
            log.info("Updated non-pathological personal history: "+nonPathologicalPersonalHistory.toString());
            return nonPathologicalPersonalHistoryRepository.save(nonPathologicalPersonalHistory);
        }
        throw new RecordsException("This non-pathological personal history doesn't exist.");
    }

    public void deleteNonPathologicalHistory(Long id) throws RecordsException {
        Optional<NonPathologicalPersonalHistory> nonPathologicalPersonalHistoryExist = nonPathologicalPersonalHistoryRepository.findById(id);
        if(!nonPathologicalPersonalHistoryExist.isPresent()){
            throw new RecordsException("No non-pathological personal history found for deletion");
        }
        nonPathologicalPersonalHistoryRepository.deleteById(id);
    }
}
