package com.medicalApp.controller;


import com.medicalApp.modal.Disease;
import com.medicalApp.service.DiseaseService;
import com.medicalApp.serviceImplementation.DiseaseServiceImplementation;
import com.medicalApp.serviceImplementation.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/diseases")
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;
    @Autowired
    private DiseaseServiceImplementation deseaseServiceImplementation;

    @GetMapping("/list")
    public String getAllDiseases(Model model) {
        List<Disease> diseases = diseaseService.getAllDiseases();
        model.addAttribute("diseases", diseases);

        model.addAttribute("disease", new Disease());

        return "diseaseList";
    }

    @GetMapping("/disease/{id}")
    public String getDiseaseById(@PathVariable Long id, Model model) {
        Disease disease = diseaseService.getDiseaseById(id);
        if (disease != null) {
            model.addAttribute("disease", disease);
        } else {
            model.addAttribute("errorMessage", "Disease not found");
        }
        return "diseaseList";
    }


    @GetMapping("/disease/edit/{id}")
    public String showEditDiseaseForm(@PathVariable Long id, Model model) {
        Disease disease = diseaseService.getDiseaseById(id);
        if (disease != null) {
            model.addAttribute("disease", disease);
            return "diseaseList";
        } else {
            model.addAttribute("errorMessage", "Disease not found");
            return "redirect:/diseases/list";
        }
    }





    @PostMapping("/disease/save")
    public String saveDisease(@ModelAttribute Disease disease, Model model) {
        Disease savedDisease = diseaseService.saveDisease(disease);
        model.addAttribute("successMessage", "Disease added successfully");
        return "redirect:/diseases/list";
    }

    @PostMapping("/disease/saveEdit")
    public String updateDisease(@ModelAttribute Disease disease, Model model) {
        Disease updatedDisease = diseaseService.saveDisease(disease);
        model.addAttribute("successMessage", "Disease updated successfully");
        return "redirect:/diseases/list";
    }

//    @GetMapping("/disease/delete/{id}")
//    public String deleteDisease(@PathVariable Long id, Model model) {
//        try {
//            diseaseService.deleteDisease(id);
//            model.addAttribute("successMessage", "Disease deleted successfully");
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", "Error deleting disease");
//        }
//        return "redirect:/diseases/list";
//    }

    @PostMapping("/disease/delete/{id}")
    public String deleteDisease(@PathVariable("id") Long id, RedirectAttributes ra) {
        try{

//            diseaseService.deleteDisease(id);
            deseaseServiceImplementation.deleteDisease(id);

            ra.addFlashAttribute("message", "Disease deleted successfully");

        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/diseases/list";
    }


}
