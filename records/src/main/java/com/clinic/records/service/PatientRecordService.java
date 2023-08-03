package com.clinic.records.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.records.dto.PatientRecordsDto;
import com.clinic.records.entity.Patient;
import com.clinic.records.error.RecordsException;
import com.clinic.records.repository.FamilyHistoryRepository;
import com.clinic.records.repository.NonPathologicalPersonalHistoryRepository;
import com.clinic.records.repository.PathologicalPersonalHistoryRepository;
import com.clinic.records.repository.PatientRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PatientRecordService {
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	PatientService patientService;
	@Autowired
	FamilyHistoryService familyHistoryService;
	@Autowired
	PathologicalPersonalHistoryService pathologicalPersonalHistoryService;
	@Autowired
	NonPathologicalPersonalHistoryService nonPathologicalPersonalHistoryService;
	
	public PatientRecordsDto getPatientRecordByEmail(String email) throws Exception {
		Optional<Patient> patientExists = patientRepository.findByEmail(email);
		if(!patientExists.isPresent()) {
			throw new RecordsException("No records for this patient");
		}
		Patient patient = patientExists.get();
		PatientRecordsDto recordsDto = new PatientRecordsDto();
		recordsDto.setPatientDto(patientService.getPatientByEmail(patient.getEmail()));
		recordsDto.setFamilyHistoryDto(familyHistoryService.getFamilyHistoryDtoByPatientId(patient.getId()));
		recordsDto.setPathologicalPersonalHistoryDto(pathologicalPersonalHistoryService
				.getPathologicalPersonalHistoryDtoByPatientId(patient.getId()));
		recordsDto.setNonPathPersHistoryDto(nonPathologicalPersonalHistoryService
				.getAllNonPathologicalHistory());
		return recordsDto;
		
	}
}
