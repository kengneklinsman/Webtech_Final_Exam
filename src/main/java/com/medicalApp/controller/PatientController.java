package com.medicalApp.controller;

import com.medicalApp.modal.Disease;
import com.medicalApp.modal.Patient;
import com.medicalApp.modal.User;
import com.medicalApp.service.DiseaseService;
import com.medicalApp.service.EmailService;
import com.medicalApp.serviceImplementation.PatientServiceImplementation;
import com.medicalApp.serviceImplementation.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientServiceImplementation patientService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private DiseaseService diseaseService;

    @GetMapping("/list")
    public String getAllPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patientsDashboard";
    }

    @GetMapping("/{id}")
    public String getPatientById(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("message", "Patient not found");
        }
        return "patientsDashboard";
    }


    @GetMapping("/searchByName")
    public String findPatientsByName(@RequestParam String name, Model model) {
        List<Patient> patients = patientService.findPatientsByName(name);
        model.addAttribute("patients", patients);
        return "patientsDashboard";
    }


    @GetMapping("/searchBySymptom")
    public String searchPatientsBySymptoms(@RequestParam String symptom, Model model) {
        List<Patient> patients = patientService.findPatientsBySymptomsContaining(symptom);
        if (!patients.isEmpty()) {
            model.addAttribute("patients", patients);
            model.addAttribute("searchTerm", symptom);
        } else {
            model.addAttribute("message", "No patients found with the specified symptom.");
            model.addAttribute("patients", Collections.emptyList()); // Empty list to clear previous data
        }
        return "patientsDashboard";
    }

    @GetMapping("/add")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patientsDashboard";
    }

//    @PostMapping("/save")
//    public String savePatient(@ModelAttribute Patient patient, Model model) {
//        Patient savedPatient = patientService.savePatient(patient);
//
//        // Send confirmation email
//        String subject = "Patient Saved Successfully";
//        String text = "Dear " + patient.getName() + ",\n\nYour information has been saved successfully.";
//
//        emailService.sendConfirmationEmail(patient.getEmail(), subject, text);
//
//
//        model.addAttribute("message", "Patient added successfully");
//        return "redirect:/patients/list";
//    }


    @PostMapping("/save")
    public String savePatient(@ModelAttribute Patient patient, Model model) {

        Disease disease = diseaseService.findDiseaseBySymptom(patient.getSymptoms());
        patient.setDisease(disease);
        Patient savedPatient = patientService.savePatient(patient);

        // Check if symptoms match any disease
        List<Disease> matchingDiseases = diseaseService.findDiseasesBySymptomsContaining(patient.getSymptoms());
        if (!matchingDiseases.isEmpty()) {
            Disease matchedDisease = matchingDiseases.get(0);
            model.addAttribute("message", "Patient added successfully. Associated Disease: " + matchedDisease.getName());
        } else {
            model.addAttribute("message", "Patient added successfully. No associated disease found.");
//            Disease matcheddDisease = matchingDiseases.get(0);
//            model.addAttribute("message", "Patient is  Associated with  " + matcheddDisease.getName() + " Disease");
        }

        // Send confirmation email
        String subject = "Patient Saved Successfully";
        String text = "Dear " + patient.getName() + ",\n\nYour information has been saved successfully.";

        emailService.sendConfirmationEmail(patient.getEmail(), subject, text);

        return "redirect:/patients/list";
    }




    @GetMapping("/patient/edit/{id}")
    public String showEditPatientForm(@PathVariable("id") Long id, Model model) {
        try {
            Patient patient = patientService.getPatientById(id);

            if (patient != null) {
                model.addAttribute("patient", patient);
                return "editPatient";
            } else {
                model.addAttribute("message", "Patient not found");
                return "redirect:/patients/list";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:/patients/list";
        }
    }

    @PostMapping("/saveEdit")
    public String updatePatient(@ModelAttribute Patient patient, Model model) {
        Patient updatedPatient = patientService.savePatient(patient);

        // Send confirmation email
        String subject = "Patient Updated Successfully";
        String text = "Dear " + patient.getName() + ",\n\nYour information has been updated successfully.";

        emailService.sendConfirmationEmail(patient.getEmail(), subject, text);



        model.addAttribute("message", "Patient updated successfully");
        return "redirect:/patients/list";
    }






    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletePatient(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            patientService.deletePatient(id);
            ra.addFlashAttribute("message", "Patient deleted successfully");
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/patients/list";
    }


}
