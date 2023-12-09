package com.medicalApp.serviceImplementation;


import com.medicalApp.modal.Disease;
import com.medicalApp.repository.DiseaseRepository;
import com.medicalApp.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseServiceImplementation implements DiseaseService {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Override
    public List<Disease> getAllDiseases() {
        return diseaseRepository.findAll();
    }

    @Override
    public Disease getDiseaseById(Long id) {
        return diseaseRepository.findById(id).orElse(null);
    }

    @Override
    public Disease saveDisease(Disease disease) {
        return diseaseRepository.save(disease);
    }

    @Override
    public void deleteDisease(Long id) {
        diseaseRepository.deleteById(id);
    }


    @Override
    public List<Disease> findDiseasesBySymptomsContaining(String symptom) {
        return diseaseRepository.findBySymptomsContaining(symptom);
    }
    @Override
    public Disease findDiseaseBySymptom(String symptom) {
        List<Disease> diseases = diseaseRepository.findBySymptomsContaining(symptom);
        return diseases.isEmpty() ? null : diseases.get(0);
    }

}
