package com.medicalApp.service;


import com.medicalApp.modal.Disease;

import java.util.List;

public interface DiseaseService {

    List<Disease> getAllDiseases();

    Disease getDiseaseById(Long id);

    Disease saveDisease(Disease disease);

    void deleteDisease(Long id);
    List<Disease> findDiseasesBySymptomsContaining(String symptom);

    Disease findDiseaseBySymptom(String symptom);
}

