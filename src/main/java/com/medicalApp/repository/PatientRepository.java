package com.medicalApp.repository;

import com.medicalApp.modal.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByName(String name);

    List<Patient> findBySymptomsContaining(String symptom);

    // Add more custom query methods as needed
}

