package com.clinic.studies.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinic.studies.entity.Study;
import com.clinic.studies.error.StudiesException;
import com.clinic.studies.repository.StudyRepository;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class StudyService {
    @Autowired
    StudyRepository studyRepository;

    public Study createStudy(Study study) {
        log.info("Create Study record: " + study.toString());
        return studyRepository.save(study);
    }

    public Study updateStudy(Study study)  throws Exception {
        if(study.getId() == null) {
            throw new StudiesException("This Study record cannot be updated");
        }
        Optional<Study> studyExists = studyRepository.findById(study.getId());
        if(studyExists.isPresent()) {
            log.info("Update Study record: "+ study.toString());
            return studyRepository.save(study);
        }
        throw new StudiesException("This Study record does not exists");
    }

    public List<Study> getAllStudies() throws Exception {
        List<Study> studyRecords = studyRepository.findAll();
        if(studyRecords.isEmpty()) {
            throw new StudiesException("No Study records found");
        }
        return studyRecords;
    }

//	public List<Study> getPatientActualStudy(Long patientId, String state){
//
//	}

    public void deleteStudy(Long id) throws Exception{
        Optional<Study> studyExists = studyRepository.findById(id);
        if(!studyExists.isPresent()) {
            throw new StudiesException("No Study record found for deletion");
        }
        studyRepository.deleteById(id);
    }
}
