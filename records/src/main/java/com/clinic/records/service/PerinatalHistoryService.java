package com.clinic.records.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.records.entity.PerinatalHistory;
import com.clinic.records.error.RecordsException;
import com.clinic.records.repository.PerinatalHistoryRepository;

import lombok.extern.log4j.Log4j2;

import java.util.Optional;

@Service
@Log4j2
public class PerinatalHistoryService {
	@Autowired 
	private PerinatalHistoryRepository perinatalRepository;
	
	public PerinatalHistory createPerinatalHistory(PerinatalHistory perinatalHistory) throws Exception {
		Optional<PerinatalHistory> perinatalHistoryExists = perinatalRepository
				.findByPatient_Id(perinatalHistory.getPatientId());
		if(!perinatalHistoryExists.isPresent()) {
			log.info("Create PerintalHistory: " + perinatalHistory.toString());
		}
		throw new RecordsException("There's an already existing perinatal History for that patient");
	}
	
	public PerinatalHistory updatePerinatalHistory(PerinatalHistory  perinatalHistory)  throws Exception {
		if(perinatalHistory.getId() == null) {
			throw new RecordsException("This perinatal history cannot be updated");
		}
		Optional<PerinatalHistory > perinatalHistoryExists = perinatalRepository
				.findByIdAndPatient_Id(perinatalHistory.getId(), perinatalHistory.getPatientId());
		if(perinatalHistoryExists.isPresent()) {
			log.info("Update Perinatal History: "+ perinatalHistory.toString());
			return perinatalRepository.save(perinatalHistory);
		}
		throw new RecordsException("This patient's perinatal history does not exists");
	}
	
	public List<PerinatalHistory> getAllPerinatalHistories() throws Exception {
		List<PerinatalHistory> histories = perinatalRepository.findAll();
		if(histories.isEmpty()) {
			throw new RecordsException("No histories found");
		}
		return histories;
	}
	
	public void deletePerinatalHistory(Long id) throws Exception {
		Optional<PerinatalHistory> historyExists = perinatalRepository.findById(id);
		if(!historyExists.isPresent()) {
			throw new RecordsException("No history found for deletion");
		}
		perinatalRepository.deleteById(id);
	}
}
