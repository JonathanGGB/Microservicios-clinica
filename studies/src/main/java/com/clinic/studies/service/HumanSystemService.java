package com.clinic.studies.service;

import com.clinic.studies.dto.HumanSystemDto;
import com.clinic.studies.dto.MedicalHistoryDto;
import com.clinic.studies.dto.ReassessmentDto;
import com.clinic.studies.dto.SystemDiseaseDto;
import com.clinic.studies.entity.MedicalHistory;
import com.clinic.studies.entity.Reassessment;
import com.clinic.studies.entity.SystemDisease;
import com.clinic.studies.error.RegistriesException;
import com.clinic.studies.error.StudiesException;
import com.clinic.studies.repository.HumanSystemRepository;
import com.clinic.studies.repository.ReassessmentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import com.clinic.studies.entity.HumanSystem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class HumanSystemService {
    @Autowired
    private HumanSystemRepository humanSystemRepository;
    @Autowired
    private ReassessmentRepository reassessmentRepository;

    public HumanSystem createSystem(HumanSystem humanSystem) throws Exception{
        Optional<HumanSystem> systemExists = humanSystemRepository.findByHumanSystemName(humanSystem.getHumanSystemName());
        if(!systemExists.isPresent()) {
            log.info("Create System: " + humanSystem.toString());
            return humanSystemRepository.save(humanSystem);
        }
        throw new RegistriesException("There's an already existing system with that name");
    }

    public HumanSystem updateSystem(HumanSystem humanSystem) throws Exception{
        Optional<HumanSystem> systemExists = humanSystemRepository.findById(humanSystem.getId());
        if(systemExists.isPresent()){
            log.info("Update System: " + humanSystem.toString());
            return humanSystemRepository.save(humanSystem);
        }
        throw new RegistriesException("This system doesn't exist");
    }

    public List<HumanSystem> getAllSystems() throws Exception {
        List<HumanSystem> humanSystems = humanSystemRepository.findAll();
        if(humanSystems.isEmpty()) {
            throw new RegistriesException("No systems found");
        }
        return humanSystems;
    }

    public HumanSystemDto getHumanSystemDtoAndDiseasesAndReassessment(Long humanSystemNameId) throws Exception{
        Optional<HumanSystem> humanSystemExists = humanSystemRepository.findById(humanSystemNameId);
        if(!humanSystemExists.isPresent()){
            throw new StudiesException("No human system found");
        }
        HumanSystem humanSystem = humanSystemExists.get();
        HumanSystemDto humanSystemDto = new HumanSystemDto();
        humanSystemDto.setHumanSystemName(humanSystem.getHumanSystemName());
        List<SystemDiseaseDto> systemDiseaseDtos = new ArrayList<>();
        for (SystemDisease disease : humanSystem.getDiseases()) {
            SystemDiseaseDto diseaseDto = new SystemDiseaseDto();
            diseaseDto.setDiseaseName(disease.getDiseaseName());
            systemDiseaseDtos.add(diseaseDto);
        }

        humanSystemDto.setDiseases(systemDiseaseDtos);
        return humanSystemDto;
    }

    public void deleteSystem(Long id) {
        humanSystemRepository.deleteById(id);
    }
}
