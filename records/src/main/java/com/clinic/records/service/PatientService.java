package com.clinic.records.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.clinic.records.entity.Patient;
import com.clinic.records.error.RecordsException;
import com.clinic.records.repository.PatientRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	public Patient createPatient(Patient patient) throws Exception {
		Optional<Patient> patientExists = patientRepository.findByNameAndLastnames(patient.getName(), patient.getLastnames());
		if(!patientExists.isPresent()) {
			log.info("Create Patient: " + patient.toString());
			return patientRepository.save(patient);
		}
		throw new RecordsException("There's an already existing patient with that name");
	}
	
	public Patient updatePatient(Patient patient)  throws Exception {
		if(patient.getId() == null) {
			throw new RecordsException("This patient cannot be updated");
		}
		Optional<Patient> patientExists = patientRepository.findById(patient.getId());
		if(patientExists.isPresent()) {
			log.info("Update Patient: "+ patient.toString());
			return patientRepository.save(patient);
		}
		throw new RecordsException("This patient does not exists");
	}
	
	public List<Patient> getAllPatients() throws Exception {
		List<Patient> patients = patientRepository.findAll();
		if(patients.isEmpty()) {
			throw new RecordsException("No patients found");
		}
		return patients;
	}
	
//	public PatientDto getPatientByNameAndLastNames() throws Exception {
//		
//	}
	
	public void deletePatient(Long id) throws Exception{
		Optional<Patient> patientExists = patientRepository.findById(id);
		if(!patientExists.isPresent()) {
			throw new RecordsException("No patient found for deletion");
		}
		patientRepository.deleteById(id);
	}
	
}
