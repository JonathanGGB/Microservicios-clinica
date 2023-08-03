package com.clinic.studies.service;

import com.clinic.studies.entity.Diagnosis;
import com.clinic.studies.error.RegistriesException;
import com.clinic.studies.repository.DiagnosisRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class DiagnosisService {
    @Autowired
    private DiagnosisRepository diagnosisRepository;

    public Diagnosis createDiagnosis(Diagnosis diagnosis) throws Exception {
        log.info("Create Diagnosis: " + diagnosis.toString());
        return diagnosisRepository.save(diagnosis);
    }

    public Diagnosis updateDiagnosis(Diagnosis diagnosis) throws Exception{
        Optional<Diagnosis> diagnosisExists = diagnosisRepository.findById(diagnosis.getId());
        if(diagnosisExists.isPresent()){
            log.info("Update Diagnosis: " + diagnosis.toString());
            return diagnosisRepository.save(diagnosis);
        }
        throw new RegistriesException("This Diagnosis doesn't exist");
    }

    public List<Diagnosis> getAllDiagnoses() throws Exception {
        List<Diagnosis> diagnoses = diagnosisRepository.findAll();
        if(diagnoses.isEmpty()) {
            throw new RegistriesException("No diagnoses found");
        }
        return diagnoses;
    }

    public void deleteDiagnosis(Long id) {
        diagnosisRepository.deleteById(id);
    }
}
