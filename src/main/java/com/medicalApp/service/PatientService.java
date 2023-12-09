package com.medicalApp.service;

import com.medicalApp.modal.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> getAllPatients();

    Patient getPatientById(Long id);

    Patient savePatient(Patient patient);

    void deletePatient(Long id);

    List<Patient> findPatientsByName(String name);

    List<Patient> findPatientsBySymptomsContaining(String symptom);
}

