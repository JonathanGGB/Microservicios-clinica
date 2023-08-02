package com.clinic.studies.service;

import com.clinic.studies.error.RegistriesException;
import com.clinic.studies.repository.HumanSystemRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import com.clinic.studies.entity.HumanSystem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class HumanSystemService {
    @Autowired
    private HumanSystemRepository humanSystemRepository;

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

    public void deleteSystem(Long id) {
        humanSystemRepository.deleteById(id);
    }
}
