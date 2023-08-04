package com.clinic.studies.service;

import java.util.List;

import com.clinic.studies.client.IPatientClient;
import com.clinic.studies.dto.CurrentSufferingDto;
import com.clinic.studies.dto.client.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.clinic.studies.entity.CurrentSuffering;
import com.clinic.studies.error.StudiesException;
import com.clinic.studies.repository.CurrentSufferingRepository;

import java.util.Optional;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CurrentSufferingService {
	@Autowired
	CurrentSufferingRepository currentSufferingRepository;
	@Autowired(required = true)
	private IPatientClient patientClient;

	
	public CurrentSuffering createCurrentSuffering(CurrentSuffering currentSuffering) {
		log.info("Create CurrentSuffering record: " + currentSuffering.toString());
		return currentSufferingRepository.save(currentSuffering);
	}
	
	public CurrentSuffering updateCurrentSuffering(CurrentSuffering currentSuffering)  throws Exception {
		if(currentSuffering.getId() == null) {
			throw new StudiesException("This Current Suffering record cannot be updated");
		}
		Optional<CurrentSuffering> currentSufferingExists = currentSufferingRepository.findById(currentSuffering.getId());
		if(currentSufferingExists.isPresent()) {
			log.info("Update CurrentSuffering record: "+ currentSuffering.toString());
			return currentSufferingRepository.save(currentSuffering);
		}
		throw new StudiesException("This CurrentSuffering record does not exists");
	}
	
	public List<CurrentSuffering> getAllCurrentSufferings() throws Exception {
		List<CurrentSuffering> currentSufferingRecords = currentSufferingRepository.findAll();
		if(currentSufferingRecords.isEmpty()) {
			throw new StudiesException("No CurrentSuffering records found");
		}
		return currentSufferingRecords;
	}
	
	public CurrentSufferingDto getCurrentSufferingByPatientId(Long patientId) throws StudiesException {
		CurrentSufferingDto currentSufferingDto = new CurrentSufferingDto();
		Optional<CurrentSuffering> currentSufferingExist = currentSufferingRepository.findByPatientId(patientId);
		if(!currentSufferingExist.isPresent()){
			throw new StudiesException("There is no current sufferings for this patient.");
		}
		ResponseEntity<PatientDto> response = patientClient.findPatientById(patientId);
		PatientDto responseDto = response.getBody();
		CurrentSuffering currentSuffering = currentSufferingExist.get();
		currentSufferingDto.setPatientName(responseDto.getFullName());
		currentSufferingDto.setDate(currentSuffering.getDate());
		currentSufferingDto.setDescription(currentSuffering.getDescription());
		currentSufferingDto.setEvolution(currentSuffering.getEvolution());
		currentSufferingDto.setState(currentSuffering.getState());
		return currentSufferingDto;
	}
	
	public void deleteCurrentSuffering(Long id) throws Exception{
		Optional<CurrentSuffering> currentSufferingExists = currentSufferingRepository.findById(id);
		if(!currentSufferingExists.isPresent()) {
			throw new StudiesException("No CurrentSuffering record found for deletion");
		}
		currentSufferingRepository.deleteById(id);
	}
}
