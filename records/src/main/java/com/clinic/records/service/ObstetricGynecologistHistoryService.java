package com.clinic.records.service;

import com.clinic.records.dto.ObstGynecHistoryDto;
import com.clinic.records.entity.ObstetricGynecologistHistory;
import com.clinic.records.entity.Patient;
import com.clinic.records.error.RecordsException;
import com.clinic.records.repository.ObstetricGynecologistHistoryRepository;
import com.clinic.records.repository.PatientRepository;

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
    @Autowired
    private PatientRepository patientRepository;

    public List<ObstetricGynecologistHistory> getAllObstetricHistory() throws RecordsException {
        List<ObstetricGynecologistHistory> obstetricGynecologistHistories = obstetricGynecologistHistoryRepository.findAll();
        if(obstetricGynecologistHistories.isEmpty()){
            throw new RecordsException("No obstetric-gynecologist histories found.");
        }
        return obstetricGynecologistHistories;
    }
    
    public ObstGynecHistoryDto getObstGynecHistoryDtoByPatientId(Long id) throws RecordsException {
    	Optional<Patient> patientExists = patientRepository.findById(id);
		if(!patientExists.isPresent()) {
			throw new RecordsException("This patient does not exists");
		}
		Patient patient = patientExists.get();
		ObstGynecHistoryDto historyDto = new ObstGynecHistoryDto();
		Optional<ObstetricGynecologistHistory> historyExists = obstetricGynecologistHistoryRepository.findByPatientId(id);
		if(!historyExists.isPresent()) {
			throw new RecordsException("No ObstetricGynechologist History found for this patient");
		}
		ObstetricGynecologistHistory history = historyExists.get();
		historyDto.setAbortions(history.getAbortions());
		historyDto.setBasl(history.getBasl());
		historyDto.setBirthNum(history.getBirthNum());
		historyDto.setCesareans(history.getCesareans());
		historyDto.setFullname(patient.getName()+" "+patient.getLastnames());
		historyDto.setLpd(history.getLpd());
		historyDto.setMenarche(history.getMenarche());
		historyDto.setMensualCicle(history.getMensualCicle());
		return historyDto;
    }

    public ObstetricGynecologistHistory createObstetricHistory(ObstetricGynecologistHistory obstetricGynecologistHistory) throws RecordsException {
        Optional<Patient> patientExists = patientRepository.findById(obstetricGynecologistHistory.getPatientId());  
        Optional<ObstetricGynecologistHistory> obstetricGynecologistHistoryExist = obstetricGynecologistHistoryRepository
        		.findByPatientId(obstetricGynecologistHistory.getPatientId());
        if((patientExists.isPresent())&&(!obstetricGynecologistHistoryExist.isPresent()) && (patientExists.get().isSex())){
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
