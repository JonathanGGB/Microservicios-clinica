package com.clinic.records.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.records.dto.PerinatalHistoryDto;
import com.clinic.records.entity.Patient;
import com.clinic.records.entity.PerinatalHistory;
import com.clinic.records.error.RecordsException;
import com.clinic.records.repository.PatientRepository;
import com.clinic.records.repository.PerinatalHistoryRepository;

import lombok.extern.log4j.Log4j2;

import java.util.Optional;

@Service
@Log4j2
public class PerinatalHistoryService {
	@Autowired 
	private PerinatalHistoryRepository perinatalHistoryRepository;
	@Autowired 
	private PatientRepository patientRepository;
	
	public PerinatalHistory createPerinatalHistory(PerinatalHistory perinatalHistory) throws Exception {
		Optional<Patient> patientExists = patientRepository.findById(perinatalHistory.getPatientId());
		Optional<PerinatalHistory> perinatalHistoryExists = perinatalHistoryRepository
				.findByPatientId(perinatalHistory.getPatientId());
		if((patientExists.isPresent())&&(!perinatalHistoryExists.isPresent())) {
			log.info("Create PerintalHistory: " + perinatalHistory.toString());
		}
		throw new RecordsException("There's an already existing perinatal History for that patient");
	}
	
	public PerinatalHistoryDto getPerinatalHistoryDtoById(Long id) throws Exception {
		Optional<Patient> patientExists = patientRepository.findById(id);
		if(!patientExists.isPresent()) {
			throw new RecordsException("This patient does not exists");
		}
		Patient patient = patientExists.get();
		PerinatalHistoryDto historyDto = new PerinatalHistoryDto();
		Optional<PerinatalHistory> perinatalExists = perinatalHistoryRepository.findByPatientId(id);
		if(!perinatalExists.isPresent()) {
			throw new RecordsException("No found record for this patient");
		}
		PerinatalHistory history = perinatalExists.get();
		historyDto.setApgar(history.getApgar());
		historyDto.setBirthday(patient.getBirthday());
		historyDto.setBirthProblems(history.getBirthProblems());
		historyDto.setFullname(patient.getName()+" "+patient.getLastnames());
		historyDto.setGestationWeeks(history.getGestationWeeks());
		historyDto.setHeigth(history.getHeigth());
		historyDto.setPregnancyNum(history.getPregnancyNum());
		historyDto.setWeigh(history.getWeigh());
		
		return historyDto;
	}
	
	public PerinatalHistory updatePerinatalHistory(PerinatalHistory  perinatalHistory)  throws Exception {
		if(perinatalHistory.getId() == null) {
			throw new RecordsException("This perinatal history cannot be updated");
		}
		Optional<PerinatalHistory > perinatalHistoryExists = perinatalHistoryRepository
				.findByIdAndPatientId(perinatalHistory.getId(), perinatalHistory.getPatientId());
		if(perinatalHistoryExists.isPresent()) {
			log.info("Update Perinatal History: "+ perinatalHistory.toString());
			return perinatalHistoryRepository.save(perinatalHistory);
		}
		throw new RecordsException("This patient's perinatal history does not exists");
	}
	
	public List<PerinatalHistory> getAllPerinatalHistories() throws Exception {
		List<PerinatalHistory> histories = perinatalHistoryRepository.findAll();
		if(histories.isEmpty()) {
			throw new RecordsException("No histories found");
		}
		return histories;
	}
	
	public void deletePerinatalHistory(Long id) throws Exception {
		Optional<PerinatalHistory> historyExists = perinatalHistoryRepository.findById(id);
		if(!historyExists.isPresent()) {
			throw new RecordsException("No history found for deletion");
		}
		perinatalHistoryRepository.deleteById(id);
	}
}
