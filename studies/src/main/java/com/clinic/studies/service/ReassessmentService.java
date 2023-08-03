package com.clinic.studies.service;

import com.clinic.studies.entity.Reassessment;
import com.clinic.studies.error.RegistriesException;
import com.clinic.studies.repository.ReassessmentRepository;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ReassessmentService {
    @Autowired
    private ReassessmentRepository reassessmentRepository;

    public Reassessment createReassessment(Reassessment reassessment) throws Exception {
        log.info("Create Reassessment: " + reassessment.toString());
        return reassessmentRepository.save(reassessment);
    }

    public Reassessment updateReassessment(Reassessment reassessment) throws Exception{
        Optional<Reassessment> reassessmentExists = reassessmentRepository.findById(reassessment.getId());
        if(reassessmentExists.isPresent()){
            log.info("Update Reassessment: " + reassessment.toString());
            return reassessmentRepository.save(reassessment);
        }
        throw new RegistriesException("This Reassessment doesn't exist");
    }

    public List<Reassessment> getAllReassessments() throws Exception {
        List<Reassessment> reassessments = reassessmentRepository.findAll();
        if(reassessments.isEmpty()) {
            throw new RegistriesException("No reassessments found");
        }
        return reassessments;
    }

    public void deleteReassessment(Long id) {
        reassessmentRepository.deleteById(id);
    }
}
