package com.clinic.studies.service;


import com.clinic.studies.dto.CurrentSufferingDto;
import com.clinic.studies.dto.PatientStudiesDto;
import com.clinic.studies.dto.PhysicalExplorationDto;
import com.clinic.studies.dto.PrognosticDto;
import com.clinic.studies.error.StudiesException;
import com.clinic.studies.repository.CurrentSufferingRepository;
import com.clinic.studies.repository.PhysicalExplorationRepository;
import com.clinic.studies.repository.PrognosticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PatientStudiesService {
    @Autowired
    CurrentSufferingRepository currentSufferingRepository;
    @Autowired
    CurrentSufferingService currentSufferingService;
    @Autowired
    PrognosticService prognosticService;
    @Autowired
    PrognosticRepository prognosticRepository;
    @Autowired
    PhysicalExplorationService physicalExplorationService;
    @Autowired
    PhysicalExplorationRepository physicalExplorationRepository;



    public PatientStudiesDto getPatientStudiesByPacientId(Long id) throws StudiesException {
        PatientStudiesDto patientStudiesDto = new PatientStudiesDto();
        CurrentSufferingDto currentSufferingDto = currentSufferingService.getCurrentSufferingByPatientId(id);
        patientStudiesDto.setCurrentSufferingDto(currentSufferingDto);
        PhysicalExplorationDto physicalExplorationDto = physicalExplorationService.getPhysicalExplorationByPatientId(id);
        patientStudiesDto.setPhysicalExplorationDto(physicalExplorationDto);
        PrognosticDto prognosticDto = prognosticService.getPrognosticDtoByPatientId(id);
        patientStudiesDto.setPrognosticDto(prognosticDto);
        return patientStudiesDto;
    }
}
