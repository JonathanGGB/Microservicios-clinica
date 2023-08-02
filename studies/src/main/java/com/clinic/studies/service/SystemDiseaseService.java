package com.clinic.studies.service;

import com.clinic.studies.entity.HumanSystem;
import com.clinic.studies.entity.SystemDisease;
import com.clinic.studies.error.RegistriesException;
import com.clinic.studies.repository.HumanSystemRepository;
import com.clinic.studies.repository.SystemDiseaseRepository;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Log4j2
public class SystemDiseaseService {
    @Autowired
    private SystemDiseaseRepository systemDiseaseRepository;
    @Autowired
    private HumanSystemRepository humanSystemRepository;

    public SystemDisease createSystemDisease(Long humanSystemId, SystemDisease systemDisease) throws RegistriesException {
        HumanSystem humanSystem = humanSystemRepository.findById(humanSystemId)
                .orElseThrow(() -> new RegistriesException("HumanSystem not found"));
        Optional<SystemDisease> existsSystemDisease = systemDiseaseRepository.
                findByDiseaseNameAndHumanSystem(systemDisease.getDiseaseName(), systemDisease.getHumanSystem());
        if(!existsSystemDisease.isPresent()){
            log.info("Insert system disease: "+systemDisease.toString());
            systemDisease.setHumanSystem(humanSystem);
            return systemDiseaseRepository.save(systemDisease);
        }
        return systemDiseaseRepository.save(systemDisease);
    }

    public SystemDisease updateSystemDiseases(SystemDisease SystemDisease) throws Exception{
        Optional<com.clinic.studies.entity.SystemDisease> SystemDiseaseExists = systemDiseaseRepository.findById(SystemDisease.getId());
        if(SystemDiseaseExists.isPresent()){
            log.info("Update System disease: " + SystemDisease.toString());
            return systemDiseaseRepository.save(SystemDisease);
        }
        throw new RegistriesException("This System disease doesn't exist");
    }

    public List<SystemDisease> getAllSystemDiseases() throws Exception {
        List<SystemDisease> SystemDisease = systemDiseaseRepository.findAll();
        if(SystemDisease.isEmpty()) {
            throw new RegistriesException("No System diseases found");
        }
        return SystemDisease;
    }

    public void deleteSystemDisease(Long id) {
        systemDiseaseRepository.deleteById(id);
    }
}
