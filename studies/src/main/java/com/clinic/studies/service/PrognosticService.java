package com.clinic.studies.service;

import java.util.List;

import com.clinic.studies.client.IPatientClient;
import com.clinic.studies.dto.PrognosticDto;
import com.clinic.studies.dto.client.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.clinic.studies.entity.Prognostic;
import com.clinic.studies.error.StudiesException;
import com.clinic.studies.repository.PrognosticRepository;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PrognosticService {
    @Autowired
    PrognosticRepository prognosticRepository;
    @Autowired(required = true)
    IPatientClient patientClient;

    public Prognostic createPrognostic(Prognostic prognostic) {
        log.info("Create Prognostic record: " + prognostic.toString());
        return prognosticRepository.save(prognostic);
    }

    public Prognostic updatePrognostic(Prognostic prognostic)  throws Exception {
        if(prognostic.getId() == null) {
            throw new StudiesException("This Prognostic record cannot be updated");
        }
        Optional<Prognostic> prognosticExists = prognosticRepository.findById(prognostic.getId());
        if(prognosticExists.isPresent()) {
            log.info("Update Prognostic record: "+ prognostic.toString());
            return prognosticRepository.save(prognostic);
        }
        throw new StudiesException("This Prognostic record does not exists");
    }

    public List<Prognostic> getAllPrognostics() throws Exception {
        List<Prognostic> prognosticRecords = prognosticRepository.findAll();
        if(prognosticRecords.isEmpty()) {
            throw new StudiesException("No Prognostic records found");
        }
        return prognosticRecords;
    }

    public PrognosticDto getPrognosticDtoByPatientId(Long patientId) throws StudiesException {
        PrognosticDto prognosticDto = new PrognosticDto();
        Optional<Prognostic> prognosticExist = prognosticRepository.findByPatientId(patientId);
        if(!prognosticExist.isPresent()){
            throw new StudiesException("No Prognostic is found for this patient.");
        }
        ResponseEntity<PatientDto> response = patientClient.findPatientById(patientId);
        PatientDto responseDto = response.getBody();
        Prognostic prognostic = prognosticExist.get();
        prognosticDto.setPatientName(responseDto.getFullName());
        prognosticDto.setPatientDescription(prognostic.getPatientDescription());
        prognosticDto.setPrognosticDate(prognostic.getPrognosticDate());

        return prognosticDto;
    }

    public void deletePrognostic(Long id) throws Exception{
        Optional<Prognostic> prognosticExists = prognosticRepository.findById(id);
        if(!prognosticExists.isPresent()) {
            throw new StudiesException("No Prognostic record found for deletion");
        }
        prognosticRepository.deleteById(id);
    }
}
