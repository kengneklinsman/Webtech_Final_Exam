package com.medicalApp.serviceImplementation;

import com.medicalApp.modal.Patient;
import com.medicalApp.repository.PatientRepository;
import com.medicalApp.service.PatientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImplementation implements PatientService {
    @Autowired
    private  PatientRepository patientRepository;


    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    @Transactional
    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> findPatientsByName(String name) {
        return patientRepository.findByName(name);
    }

    @Override
    public List<Patient> findPatientsBySymptomsContaining(String symptom) {
        return patientRepository.findBySymptomsContaining(symptom);
    }
}

