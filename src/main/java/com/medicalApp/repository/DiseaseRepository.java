package com.medicalApp.repository;

import com.medicalApp.modal.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
    Disease findByName(String name);
    List<Disease> findBySymptomsContaining(String symptom);
}
