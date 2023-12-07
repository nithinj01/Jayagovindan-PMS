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

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private VisitService visitService;

    public PatientController(PatientService patientService, VisitService visitService) {
        this.patientService = patientService;
        this.visitService = visitService;
    }

    @GetMapping
    public String getAllPatients(Model model, @RequestParam(required = false) Long patientId) {
        List<Patient> patients = patientService.getAllPatients();
        List<String> availableDateTimes = visitService.getAvailableDateTimesForPatient(patientId);

        model.addAttribute("patients", patients);
        model.addAttribute("date-list", availableDateTimes);
        model.addAttribute("selectedPatientId", patientId);
        return "index";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }
    //Create patient future use case
    @PostMapping("/patients")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.savePatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }
    //Get visits for given id
   @GetMapping("/{id}/visits")
    public ResponseEntity<List<Visit>> getVisitsByPatientId(@PathVariable Long id) {
        List<Visit> visits = visitService.getAllVisitsByPatientId(id);
        return ResponseEntity.ok(visits);
    }
    //alternate method to save a patient
   @PostMapping("/save")
   public String savePatient(@ModelAttribute Patient patient) {
       patientService.savePatient(patient);
       return "redirect:/patients";
   }

}

