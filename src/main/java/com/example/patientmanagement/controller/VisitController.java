package com.example.patientmanagement.controller;

import com.example.patientmanagement.model.Patient;
import com.example.patientmanagement.model.Visit;
import com.example.patientmanagement.service.PatientService;
import com.example.patientmanagement.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/visits")
public class VisitController {
    @Autowired
    private VisitService visitService;
    @Autowired
    private PatientService patientService;

    //Get patient lists for further processing
    @GetMapping("/patient")
    public String getVisitsByPatientId(@RequestParam(required = false) Long patientId, Model model) {
        List<Visit> visits = visitService.getAllVisitsByPatientId(patientId);
        model.addAttribute("visits", visits);
        return "index";
    }
    @GetMapping("/available-date-times/{patientId}")
    public ResponseEntity<List<String>> getAvailableDateTimes(Model model, @RequestParam(required = false) Long patientId) {
        List<String> availableDateTimes = visitService.getAvailableDateTimesForPatient(patientId);
        model.addAttribute("date-list", availableDateTimes);
        model.addAttribute("selectedPatientId", patientId);
        return ResponseEntity.ok(availableDateTimes);
    }

    //Get visit list by ID
    @GetMapping("/{id}")
    public ResponseEntity<Visit> getVisitById(@PathVariable Long id) {
        Visit visit = visitService.getVisitById(id);
        return ResponseEntity.ok(visit);
    }
    //create visits for patients
    @PostMapping
    public String createVisit(@ModelAttribute Visit visit, @RequestParam Long patientId) {
        // Set the patient ID for the visit
        Patient patient = new Patient();
        patient.setId(patientId);
        visit.setPatient(patient);
        visitService.createVisit(visit);
        return "redirect:/patients?patientId=" + patientId;
    }

    @PostMapping("/patient/{patientId}")
    public ResponseEntity<Visit> createVisitForPatient(@PathVariable Long patientId, @RequestBody Visit visit) {
        Patient patient = patientService.getPatientById(patientId);
        if (patient == null) {
            return ResponseEntity.notFound().build();
        }

        visit.setPatient(patient);
        Visit savedVisit = visitService.saveVisit(visit);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVisit);
    }
    // Update a visit (handle POST/PUT request)
    @PostMapping("/update")
    public String updateVisit(@ModelAttribute Visit updatedVisit, @RequestParam Long patientId, Model model) {
        try {
            // Set the patient ID for the updated visit
            Patient patient = new Patient();
            patient.setId(patientId);
            updatedVisit.setPatient(patient);
            // Call the service method to update the visit
            visitService.updateVisit(updatedVisit);
            // Add a success message to the model
            model.addAttribute("success", true);
        } catch (RuntimeException e) {
            // Handle the case where the visit to be updated is not found
            model.addAttribute("error", "Visit not found");
        } catch (Exception e) {
            // Handle other exceptions
            model.addAttribute("error", "An error occurred while updating the visit");
        }

        // Redirect to the page where you want to show the result (e.g., the update form)
        return "redirect:/visits/update/" + updatedVisit.getId();
    }


}

